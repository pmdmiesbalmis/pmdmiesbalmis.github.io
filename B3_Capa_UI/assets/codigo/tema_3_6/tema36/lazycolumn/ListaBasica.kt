package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

@Composable
fun DatosEnColumnaBasico(
    datosState: List<Dato>,
    onClickDato: (Dato) -> Unit = {}
) {
    LazyColumn {
        items(datosState) { dato ->
            TarjetaDato(
                datoState = dato,
                onClickDato = onClickDato
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=700px,height=725px,dpi=480,orientation=portrait"
)
@Composable
private fun DatosListaBasicaPreview() {

    // Generamos unos datos en una lista inmutable List<Dato> y lo guardamos como estado.
    // ✋ Deberemos regenerar TODA LA LISTA para que se recomponga el LazyColumn
    var datosState by rememberSaveable { mutableStateOf(datosAleatorios()) }

    // Función que regenera los datos de la lista al pulsar el FAB
    val regeneraLista = { datosState = datosAleatorios() }

    // Función que borra un dato de la lista al pulsar sobre él
    val borraDato: (Dato) -> Unit = { d ->
        datosState.indexOf(d).takeIf { it >= 0 }?.let { i ->
            // Si encontramos el índice del dato en la lista deberemos convertirla
            // en mutable para poder usar el removeAt. Además, toMutableList() me creará
            // un nuevo objeto por lo que se producirá la recomposición.
            datosState = datosState.toMutableList().apply { removeAt(i) }
        }
    }
    ProyectoBaseTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaBasico(datosState = datosState, onClickDato = borraDato)
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = regeneraLista
            ) {
                Icon(
                    painter = Filled.getRefreshIcon(),
                    contentDescription = "Recargar Datos"
                )
            }
        }
    }
}


