package com.ramyres.curso.android.basic.compose.checklist

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramyres.curso.android.basic.compose.checklist.componentes.Cabecalho
import com.ramyres.curso.android.basic.compose.checklist.componentes.Cadastro
import com.ramyres.curso.android.basic.compose.checklist.componentes.Lista
import com.ramyres.curso.android.basic.compose.checklist.componentes.Sobre
import com.ramyres.curso.android.basic.compose.checklist.model.Checklist
import com.ramyres.curso.android.basic.compose.checklist.model.ChecklistItem
import com.ramyres.curso.android.basic.compose.checklist.model.obterListaInicialDeItens
import com.ramyres.curso.android.basic.compose.checklist.ui.theme.ChecklistTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChecklistTheme {
                App(modifier = Modifier.imePadding())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    // https://developer.android.com/develop/ui/compose/state?hl=pt-br#ways-to-store
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estados da Aplicação
    var tela: String by rememberSaveable { mutableStateOf("LISTA") }
    var filtroDeTarefas: String by rememberSaveable { mutableStateOf("") }
    var itemSelecionado by remember { mutableStateOf<ChecklistItem?>(null) }
    var abrirDialogExclusao by remember { mutableStateOf(false) }
    val checklist = remember {
        mutableStateOf(
            Checklist(
                name = "Minha Lista",
                items = obterListaInicialDeItens(),
            )
        )
    }

    // Função para mostrar ou ocultar o Menu
    fun showOrHideMenu(): Unit {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    // Função para marcar ou desmarcar o item
    fun checkOrUncheck(item: ChecklistItem): Unit {
        val concluido = !item.concluido;
        val nova_lista: List<ChecklistItem> =
            checklist.value.items.map { it -> if (it.id == item.id) it.copy(concluido = concluido) else it };
        val novo_checklist = Checklist(checklist.value.name, nova_lista)
        checklist.value = novo_checklist;
    }

    fun removerItem(item: ChecklistItem): Unit {
        val nova_lista: List<ChecklistItem> = checklist.value.items.filter { it -> it.id != item.id };
        val novo_checklist = Checklist(checklist.value.name, nova_lista)
        checklist.value = novo_checklist;
    }

    fun excluirItem(item: ChecklistItem): Unit {
        itemSelecionado = item
        abrirDialogExclusao = true
    }

    fun incluirItem(item: ChecklistItem){
        val nova_lista: MutableList<ChecklistItem> = checklist.value.items.toMutableList();
        nova_lista.add(item);
        val novo_checklist = Checklist(checklist.value.name, nova_lista.toList())
        checklist.value = novo_checklist;
    }

    fun adicionarItem(){
        tela = "CADASTRO"
    }

    // Dialogo de Exclusão
    if (abrirDialogExclusao && itemSelecionado != null) {
        AlertDialog(
            onDismissRequest = {
                abrirDialogExclusao = false
            },
            title = { Text(text = "Excluir Item") },
            text = { Text(text = "Confirma exclusão do item?") },
            confirmButton = {
                TextButton(onClick = {
                    abrirDialogExclusao = false
                    removerItem(itemSelecionado!!)
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    abrirDialogExclusao = false
                }) {
                    Text("Não")
                }
            }
        )
    }

    // Layout
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(stringResource(R.string.app_title), modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Todas as Tarefas") },
                    selected = (filtroDeTarefas == ""),
                    onClick = {
                        tela = "LISTA"
                        filtroDeTarefas = ""
                        showOrHideMenu()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Tarefas Abertas") },
                    selected = (filtroDeTarefas == "A"),
                    onClick = {
                        tela = "LISTA"
                        filtroDeTarefas = "A"
                        showOrHideMenu()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Tarefas Concluídas") },
                    selected = (filtroDeTarefas == "C"),
                    onClick = {
                        tela = "LISTA"
                        filtroDeTarefas = "C"
                        showOrHideMenu()
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Sobre o App") },
                    selected = filtroDeTarefas == "S",
                    onClick = {
                        tela = "SOBRE"
                        filtroDeTarefas = "S"
                        showOrHideMenu()
                    }
                )
            }
        },
        modifier = modifier
    ) {
        Scaffold(
            topBar = {Cabecalho(scrollBehavior = scrollBehavior,onClickMenu = { showOrHideMenu() })},
            floatingActionButton = {FloatingActionButton(onClick = { adicionarItem() }) {Icon(Icons.Default.Add, contentDescription = "Adicionar Item")}},
        ) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {

                when (tela) {
                    "LISTA" -> Lista(
                        lista = checklist.value.items.filter { it -> if (filtroDeTarefas == "") true else if (filtroDeTarefas == "C") it.concluido else !it.concluido },
                        onClickItem = { item -> checkOrUncheck(item) },
                        onPressItem = { item ->  excluirItem(item)})
                    "SOBRE" -> Sobre(){}
                    "CADASTRO" -> Cadastro(lista = checklist.value.items) {item ->
                        incluirItem(item)
                        tela = "LISTA"
                        filtroDeTarefas = "A"
                    }
                }
            }
        }
    }

}

@Preview(
    showBackground = true, showSystemUi = true, name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true, showSystemUi = true, name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AppPreview() {
    ChecklistTheme {
        App()
    }
}