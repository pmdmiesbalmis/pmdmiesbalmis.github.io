package com.pmdm.proyectobase.ui.features.tema31

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme
import com.pmdm.proyectobase.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Saluda(
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
            text = "${stringResource(R.string.saludo)} ${nombreState}"
        )
        Button(onClick = onClickBorrar) {
            Text(text = "Borrar")
        }
    }
}

@Composable
private fun IntroduceNombre(
    nombreState: String,
    onCambioNombre: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "${stringResource(R.string.nombre)}:"
        )
        TextField(
            value = nombreState,
            onValueChange = onCambioNombre
        )
    }
}


@Composable
private fun SaludaScreen(
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
    locale = "en",
    device = "spec:width=360dp,height=800dp,dpi=480,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, fontScale = 1.0f
)
@Composable
fun SaludaScreenPreview() {
    var nombreState by remember { mutableStateOf("") }
    val onCambioNombre = { nombre: String -> nombreState = nombre }
    val onClickBorrar = { nombreState = "" }

    ProyectoBaseTheme {
        Surface {
            SaludaScreen(
                nombreState = nombreState,
                onCambioNombre = onCambioNombre,
                onClickBorrar = onClickBorrar
            )
        }
    }
}

