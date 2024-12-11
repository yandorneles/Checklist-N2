package com.ramyres.curso.android.basic.compose.checklist.componentes

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramyres.curso.android.basic.compose.checklist.R
import com.ramyres.curso.android.basic.compose.checklist.ui.theme.ChecklistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cabecalho(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onClickMenu: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Lista de tarefas")
        },
        navigationIcon = {
            IconButton(onClick = onClickMenu) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Preview(
    name = "Dark",
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun CabecalhoPreview() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    ChecklistTheme {
        Cabecalho(scrollBehavior = scrollBehavior, onClickMenu = {})
    }
}
