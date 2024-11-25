package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

// Cada dato irá en un elevated card y solo mostrará el texto que nos indica el id
@Composable
fun TarjetaDato(
    modifier: Modifier = Modifier,
    datoState: Dato,
    onClickDato: (Dato) -> Unit = {}
) = ElevatedCard(
    modifier = modifier.then(
        Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClickDato(datoState) }
    ),
    colors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ),
    elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    )
) {
    Text(
        text = datoState.texto,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(8.dp),
    )
}

@Preview(
    showBackground = true,
    device = "spec:width=700px,height=725px,dpi=480,orientation=portrait"
)
@Composable
fun TarjetaDatoPreview() {

    val datoState by remember {  mutableStateOf(
        Dato(
            id = "1",
            texto = "Dato 1"
        )
    )
    }

    ProyectoBaseTheme {
        Surface {
            TarjetaDato(
                datoState = datoState,
                onClickDato = {}
            )
        }
    }

}