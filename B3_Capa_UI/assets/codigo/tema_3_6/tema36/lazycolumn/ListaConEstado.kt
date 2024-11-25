package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme
import kotlinx.coroutines.launch

@Composable
fun DatosEnColumnaDisposicionAnimada(
    datosState: List<Dato>,
    onClickDato: (Dato) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LazyColumn(state = lazyListState) {
        items(
            items = datosState,
            // Necesitamos que cada item genere una clave única para identificar cambios de
            // posición en la lista y así indicarle donde tiene que ir en
            // el redibujo de la misma.
            key = { it.id }
        ) { dato ->
            TarjetaDato(
                // Le pasamos este modifier a nuestra tarjeta para que si cambia su posición
                // ocupe la nueva posición en la lista mediante una animación.
                modifier = Modifier.animateItem(),
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
private fun DatosListaDisposicionAnimadaYEstado() {
    var datosState by rememberSaveable { mutableStateOf(datosAleatorios()) }
    val borraDato: (Dato) -> Unit = { d ->
        datosState.indexOf(d).takeIf { it >= 0 }?.let { i ->
            datosState = datosState.toMutableList().apply { removeAt(i) }
        }
    }
    val regeneraLista = { datosState = datosAleatorios() }

    // Definimos un objeto estado de la lista que pasaremos al composable que la muestra
    var lazyListState = rememberLazyListState()
    // Definimos un estado derivado de la lista que me indique si el
    // primer elemento de la misma está visible o no.
    val primeroVisible by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 }
    }

    // Este código se entenderá más adelante en el curso. Pero básicamente
    // define un handler que mueve la lista hasta su posición inicial usando concurrencia.
    // Debe ser así porque este tipo de comportamientos dinámicos no se puede definir
    // utilizando estados de composición.
    val cs = rememberCoroutineScope()
    val volverAlPrincipio = { cs.launch { lazyListState.animateScrollToItem(0) }
        Unit }

    ProyectoBaseTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaDisposicionAnimada(
                datosState = datosState, onClickDato = borraDato,
                lazyListState = lazyListState // hoisting del estado de la lista
            )
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
                onClick = if (primeroVisible) regeneraLista else volverAlPrincipio
            ) {
                Icon(
                    painter = if (primeroVisible) Filled.getRefreshIcon()
                    else Filled.getArrowBackIosIcon(),
                    contentDescription = "Datos"
                )
            }
        }
    }
}