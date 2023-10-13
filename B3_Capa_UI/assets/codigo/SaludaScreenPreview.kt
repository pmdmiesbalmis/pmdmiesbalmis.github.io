package com.holamundo.ui.features.saludo

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holamundo.ui.theme.ProyectoBaseTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Saluda(
    nombreState: String,
    onClickBorrar: () -> Unit
) {
    FlowRow(
        Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Hola ${nombreState}"
        )
        Button(onClick = onClickBorrar) {
            Text(text = "Borrar")
        }
    }
}

@Composable
fun IntroduceNombre(
    nombreState: String,
    onCambioNombre: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Nombre:"
        )
        TextField(
            value = nombreState,
            onValueChange = onCambioNombre
        )
    }
}


@Composable
fun SaludaScreen(
    nombreState: String,
    onCambioNombre: (String) -> Unit,
    onClickBorrar: () -> Unit) {

    Column(
        modifier = Modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IntroduceNombre(
            nombreState = nombreState,
            onCambioNombre = onCambioNombre
        )
        Saluda(
            nombreState = nombreState,
            onClickBorrar = onClickBorrar
        )
    }
}


@Preview(
    name = "PORTRAIT",
    device = "spec:width=360dp,height=800dp,dpi=480",
    showBackground = true
)
@Preview(
    name = "LANDSCAPE",
    device = "spec:width=360dp,height=800dp,dpi=480,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, fontScale = 1.0f
)
@Composable
fun SaludaScreenPreview() {
    var nombreState by remember { mutableStateOf("") }
    val onCambioNombre = { nombre: String -> nombreState = nombre }
    val onClickBorrar = { nombreState = "" }

    HolaMundoTheme {
        SaludaScreen(
            nombreState = nombreState,
            onCambioNombre = onCambioNombre,
            onClickBorrar = onClickBorrar
        )
    }
}

