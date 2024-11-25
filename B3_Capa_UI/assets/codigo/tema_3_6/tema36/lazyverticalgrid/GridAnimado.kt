package com.pmdm.proyectobase.ui.features.tema36.lazyverticalgrid

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme


@Composable
fun TarjetaDatoGrid(
    modifier: Modifier = Modifier,
    datoState: DatoGrid, onClickDato: (DatoGrid) -> Unit = {}
) = ElevatedCard(
    modifier = modifier.then(
        Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .clickable { onClickDato(datoState) }
    ),
    colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(
            painter = painterResource(datoState.idIcono), contentDescription = "Icono",
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
                modifier = Modifier.animateItem(),
                // Accederemos al índice recibido en el manejador.
                datoState = datosState[index],
                onClickDato = onClickDato
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=700px,height=755px,dpi=480,orientation=portrait"
)
@Composable
private fun DatosGridDisposicionAnimadaPreview() {

    // Definimos un State<List<DataGrid>> para el estado
    var datosGridState by rememberSaveable { mutableStateOf(DatoGrid.datosGridAleatorios()) }

    // Definimos los manejadores de eventos que llamarán a las funciones de utilidad
    // que hemos definido para añadir y borrar datos de la lista.
    // Además, deberán modificar toda el objeto lista para que se produzca la recomposición.
    val borraDato: (DatoGrid) -> Unit = { d -> datosGridState =
        DatoGrid.borraDatoGrid(d, datosGridState)
    }
    val addDato: () -> Unit = { datosGridState = DatoGrid.addDatoGridAleatorio(datosGridState) }

    ProyectoBaseTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnGridDisposicionAnimada(
                // columns = GridCells.Fixed(2),
                columns = GridCells.Adaptive(50.dp),
                datosState = datosGridState,
                onClickDato = borraDato
            )
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = addDato
            ) {
                Icon(
                    painter = Filled.getAddIcon(),
                    contentDescription = "Datos"
                )
            }
        }
    }
}
