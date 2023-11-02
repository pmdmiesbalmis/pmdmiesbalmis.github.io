package com.holamundo.ui.features.ejemplos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Blender
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holamundo.ui.theme.HolaMundoTheme

data class DatoGrid(
    val id: String,
    val texto: String,
    val color: Color,
    val icono: ImageVector
)

private val iconos = listOf(
    Icons.Default.Favorite, Icons.Default.AcUnit,
    Icons.Default.AccessTime, Icons.Default.Blender,
    Icons.Default.Cake, Icons.Default.Cottage)

private val colores = listOf(
    Color(0xFFA20001), Color(0xFF9C27B0),
    Color(0xFF673AB7), Color(0xFF3F51B5),
    Color(0xFF605D7B), Color(0xFF005618))

private val idsGenerados = mutableSetOf<Int>()

private fun generaId()
: Int {
    var id = (0..9999).random()
    while (idsGenerados.contains(id))
        id = (0..9999).random()
    idsGenerados.add(id)
    return id
}

private fun datosGridAleatorios()
: List<DatoGrid> {
    return (0..20).map {
        val id = generaId()
        DatoGrid(
            id = id.toString(),
            texto = "Dato\n$id",
            color = colores.random(),
            icono = iconos.random()
        )
    }
}

@Composable
fun TarjetaDatoGrid(
    modifier: Modifier = Modifier,
    datoState: DatoGrid, onClickDato: (DatoGrid) -> Unit = {}
) = ElevatedCard(
    modifier = modifier.then(
        Modifier.padding(2.dp).fillMaxWidth()
            .clickable { onClickDato(datoState) }
    ),
    colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Icon(
            imageVector = datoState.icono, contentDescription = "Icono",
            tint = datoState.color, modifier = Modifier.padding(6.dp)
        )
        Text(
            text = datoState.texto, color = datoState.color,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(6.dp),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DatosEnGridDisposicionAnimada(
    columns: GridCells, // Lo hemos definido como parámetro para poder hacer pruebas
    datosState: List<DatoGrid>,
    onClickDato: (DatoGrid) -> Unit
) {
    LazyVerticalGrid(
        columns = columns,
        state = rememberLazyGridState()
    ) {
        items(
            // La función items esta vez recibe el número de elementos de la lista
            count = datosState.size,
            // Permite saber que items permanecen en la lista y cuales no
            // para poder animar los que se permanezcan tras un cambio.
            key = { index -> datosState[index].id }
        ) { index ->
            TarjetaDatoGrid(
                // Animaremos el desplazamiento de los items de la cuadrícula
                // ante un cambio de posición
                modifier = Modifier.animateItemPlacement(),
                // Accederemos al índice recibido en el manejador.
                datoState = datosState[index],
                onClickDato = onClickDato
            )
        }
    }
}

private fun borraDatoGrid(dato: DatoGrid, datos: List<DatoGrid>): List<DatoGrid> {
    val i = datos.indexOf(dato)
    return if (i >= 0) datos.toMutableList().apply { removeAt(i) } else datos
}

private fun addDatoGridAleatorio(datos: List<DatoGrid>): List<DatoGrid> {
    return mutableListOf<DatoGrid>().apply {
        add(DatoGrid(
            id = generaId().toString(),
            texto = "Dato\n${generaId()}",
            color = colores.random(),
            icono = iconos.random()
        ))
        datos.forEach { add(it) }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=700px,height=755px,dpi=480,orientation=portrait"
)
@Composable
private fun DatosGridDisposicionAnimada() {

    // Definimos un State<List<DataGrid>> para el estado
    var datosGridState by rememberSaveable { mutableStateOf(datosGridAleatorios()) }

    // Definimos los manejadores de eventos que llamarán a las funciones de utilidad
    // que hemos definido para añadir y borrar datos de la lista.
    // Además, deberán modificar toda el objeto lista para que se produzca la recomposición.
    val borraDato: (DatoGrid) -> Unit = { d -> datosGridState = borraDatoGrid(d, datosGridState) }
    val addDato: () -> Unit = { datosGridState = addDatoGridAleatorio(datosGridState) }

    HolaMundoTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnGridDisposicionAnimada(
                // columns = GridCells.Fixed(2),
                columns = GridCells.Adaptive(50.dp),
                datosState = datosGridState,
                onClickDato = borraDato
            )
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
                onClick = addDato
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Datos"
                )
            }
        }
    }
}
