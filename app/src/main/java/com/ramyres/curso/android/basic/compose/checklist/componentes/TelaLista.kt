package com.ramyres.curso.android.basic.compose.checklist.componentes

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramyres.curso.android.basic.compose.checklist.model.ChecklistItem
import com.ramyres.curso.android.basic.compose.checklist.ui.theme.ChecklistTheme

@Composable
fun Lista(
    modifier: Modifier = Modifier,
    lista: List<ChecklistItem>,
    onClickItem: (ChecklistItem) -> Unit,
    onPressItem: (ChecklistItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 5.dp, horizontal = 15.dp)
    ) {
        items(lista) { item ->
            ListaItem(item = item, onClick = onClickItem, onLongClick = onPressItem)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListaItem(
    modifier: Modifier = Modifier,
    item: ChecklistItem,
    onClick: (ChecklistItem) -> Unit,
    onLongClick: (ChecklistItem) -> Unit
) {
    Surface(
        modifier = modifier.combinedClickable(
            onClick = { onClick(item) },
            onLongClick = { onLongClick(item) },
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 7.dp, horizontal = 5.dp)
                .border(0.5.dp, color = MaterialTheme.colorScheme.secondary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.id.toString(), modifier.padding(15.dp))
            Text(item.descricao,
                modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.7f))
            Checkbox(
                checked = item.concluido,
                onCheckedChange = { onClick(item)
            })
        }
    }
}

val listaPreview = mutableListOf(
    ChecklistItem(1,"Estudar para a prova de Gestão Mobile", true),
    ChecklistItem(2,"Ler livro sobre Inteligência Emocional", false),
    ChecklistItem(3,"Assistir trilogia Matrix", false),
    ChecklistItem(4,"Terminar site da Assembleia de Deus", false),
    ChecklistItem(5,"Estudar em casa", false),
    ChecklistItem(5,"Procurar os conteúdos das disciplinas do próximo semestre e iniciar meus estudos para que me saia melhor e que tenho maior proveito das disciplinas", false),
)
@Preview(
    name = "Light",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun ListaPreview() {
    ChecklistTheme {
        Lista(lista = listaPreview, onClickItem = {}, onPressItem = {})
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun ListaItemPreview() {
    ChecklistTheme {
        Column(Modifier.fillMaxWidth()) {
            ListaItem(item = listaPreview[0], onClick = {}, onLongClick = {})
            ListaItem(item = listaPreview[1], onClick = {}, onLongClick = {})
            ListaItem(item = listaPreview[5], onClick = {}, onLongClick = {})
        }
    }
}
