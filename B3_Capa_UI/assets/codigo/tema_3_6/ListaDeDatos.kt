package com.holamundo.ui.features.ejemplos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoveUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holamundo.ui.theme.HolaMundoTheme
import kotlinx.coroutines.launch

data class Dato(val id: String, val texto: String)

private fun generaId(idsGenerados: MutableSet<Int>): Int {
    var id = (0..9999).random()
    while (idsGenerados.contains(id))
        id = (0..9999).random()
    idsGenerados.add(id)
    return id
}

// Genera un id aleatorio
private fun datosAleatorios(): List<Dato> {
    val idsGenerados = mutableSetOf<Int>()
    return (0..20).map {
        val id = generaId(idsGenerados)
        Dato(
            id = id.toString(),
            texto = "Dato $id"
        )
    }
}

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

@OptIn(ExperimentalFoundationApi::class)
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
                modifier = Modifier.animateItemPlacement(),
                datoState = dato,
                onClickDato = onClickDato
            )
        }
    }
}

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
    HolaMundoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaBasico(datosState = datosState, onClickDato = borraDato)
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = regeneraLista
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Recargar Datos"
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

    HolaMundoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaIndizada(datosState = datosState, onClickDato = borraDatoIndizado)
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
                onClick = regeneraLista
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Recargar Datos"
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

    HolaMundoTheme {
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
                    imageVector = if (primeroVisible) Icons.Default.Refresh
                    else Icons.Default.MoveUp,
                    contentDescription = "Datos"
                )
            }
        }
    }
}

@Preview(showBackground = true,
    device = "spec:width=700px,height=725px,dpi=480,orientation=portrait")
@Composable
private fun DatosListaSnapShot() {
    // Usamos toMutableStateList() para poder modificar la lista y además
    // observar cambios en los objetos. No hace falta usar by
    var datosState = remember { datosAleatorios().toMutableStateList() }
    // Al hacer clic sobre el card tendremos 2 efectos:
    val modificaDato: (Dato) -> Unit = { d ->
        datosState.indexOf(d).takeIf { it >= 0 }?.let { i ->
            // Detecto si estamos en el primer o segundo click viendo si el texto
            // del card empieza por "Modificado "
            // Fíjate que ninuno de los casos de la lógica reasignamos datosState como
            // antes. Sino que modificamos el objeto que está en el índice devuelto.
            if (datosState[i]
                    .texto.subSequence(0, datosState[i].texto.indexOf(" "))
                != "Modificado"
            )
            // El primer click modificará el texto del card por "Modificado id"
                datosState[i] = datosState[i].copy(texto = "Modificado ${d.id}")
            else
            // El segundo click eliminará el card de la lista
                datosState.removeAt(i)
        }
    }
    HolaMundoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaDisposicionAnimada(
                datosState = datosState, onClickDato = modificaDato)
        }
    }
}
