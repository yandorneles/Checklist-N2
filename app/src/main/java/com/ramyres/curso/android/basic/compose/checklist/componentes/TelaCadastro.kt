package com.ramyres.curso.android.basic.compose.checklist.componentes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramyres.curso.android.basic.compose.checklist.model.ChecklistItem
import com.ramyres.curso.android.basic.compose.checklist.ui.theme.ChecklistTheme

@Composable
fun Cadastro(modifier: Modifier = Modifier, lista: List<ChecklistItem>, onGravar: (item: ChecklistItem) -> Unit) {
    val maxId = lista.maxByOrNull { it.id }?.id ?: 0
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp)) {
        Row {
            Text("Tarefa:")
        }
        Row(modifier.fillMaxWidth()) {
            TextField(value = descricao,
                onValueChange = {it -> descricao = it},
                modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        }
        Row (
            modifier
                .fillMaxWidth()
                .padding(top = 20.dp)) {
            OutlinedButton(
                onClick = {
                    val novo_item = ChecklistItem(maxId+1, descricao)
                    onGravar(novo_item)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Adicionar",
                    fontSize = 18.sp,
                    modifier = modifier.padding(15.dp)
                )
            }
        }
    }
}

@Preview(
    name = "Light",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Dark",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun CadastroPreview() {
    ChecklistTheme {
        Cadastro(lista = listOf(), onGravar = {})
    }
}
