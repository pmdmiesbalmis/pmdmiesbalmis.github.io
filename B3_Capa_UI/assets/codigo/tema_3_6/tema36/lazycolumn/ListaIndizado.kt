package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun DatosEnColumnaIndizada(
    datosState: List<Dato>,
    // ✋ La función de gestión ahora puede recibir el índice en lugar del dato
    onClickDato: (Int) -> Unit = {}
) {
    LazyColumn {
        // Esta función además de cada dato me pasará el índice de la lista donde
        // se encuentra el mismo de esta manera podremos por ejemplo realizar
        // selecciones múltiples para borrar o pasarle a la función de selección
        // el índice de la lista y de esa manera ahorrarnos la búsqueda del mismo
        // como en el caso anterior.
        itemsIndexed(items = datosState) { i, dato ->
            // Debo pulsar fuera del card, sobre el número del índice, para que tenga
            // efecto el click. En caso contrario se ejecutará el click del TarjetaDato que
            // hemos dejado sin efecto al no establecerlo.
            Row(
                modifier = Modifier.clickable { onClickDato(i) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$i:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.2f)
                )
                TarjetaDato(
                    modifier = Modifier.weight(0.8f),
                    datoState = dato
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = "spec:width=700px,height=725px,dpi=480,orientation=portrait"
)
@Composable
private fun DatosListaIndizada() {
    var datosState by rememberSaveable { mutableStateOf(datosAleatorios()) }
    val regeneraLista = { datosState = datosAleatorios() }

    // La función de gestión ahora puede recibir el índice en lugar del dato, por lo que
    // me ahorro la búsqueda de la posición del dato en la lista.
    val borraDatoIndizado: (i: Int) -> Unit = { i ->
        datosState = datosState.toMutableList().apply { removeAt(i) }
    }

    ProyectoBaseTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaIndizada(datosState = datosState, onClickDato = borraDatoIndizado)
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
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

