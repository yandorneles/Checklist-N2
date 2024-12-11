package com.ramyres.curso.android.basic.compose.checklist.componentes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramyres.curso.android.basic.compose.checklist.ui.theme.ChecklistTheme

@Composable
fun Sobre(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Text(fontSize = 30.sp, text = "Sobre")
        }
        Column(modifier.padding(vertical = 12.dp)) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                text = "Aplicação para criação de checklist"
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                text = "Faculdade Aphonsiado - 2024/1"
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                text = "Versão 0.0.1"
            )
        }
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    //showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    name = "Dark",
    showBackground = true,
    //showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun SobrePreview() {
    ChecklistTheme() {
        Sobre(onClose = {})
    }
}