@file:OptIn(ExperimentalMaterial3Api::class)

package com.vehiculos.ui.features.galeriacoches

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.FilterChipWithIcon
import com.vehiculos.R
import com.vehiculos.ui.features.CocheUiState

@Composable
private fun BarraAppInferiorConVehiculoSeleccionado(
    onEditarFichaCoche: () -> Unit,
    onBorrarVehiculo: () -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(
                onClick = onEditarFichaCoche
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Editar Ficha"
                )
            }
            IconButton(
                onClick = onBorrarVehiculo
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Borrar Vehículo"
                )
            }
        }
    )
}

@Composable
private fun BarraAppInferiorSinVehiculoSeleccionado(
    ordenarPorPrecioState: Boolean,
    onOrdenarPorPrecio: () -> Unit,
    filtrarPorDescuentoState: Boolean,
    onFiltrarPorDescuento: () -> Unit
) {
    BottomAppBar(
        actions = {
            FilterChipWithIcon(
                seleccionadoState = ordenarPorPrecioState,
                textoState = "Precio",
                iconState = painterResource(id = R.drawable.sort_by_alpha_24px),
                onClick = { onOrdenarPorPrecio() }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            FilterChipWithIcon(
                seleccionadoState = filtrarPorDescuentoState,
                textoState = "Descuento",
                iconState = painterResource(id = R.drawable.stars_24px),
                onClick = { onFiltrarPorDescuento() }
            )
        }
    )
}

@Composable
fun BarraAppInferior(
    cocheSeleccionadoState: CocheUiState?,
    onEditarFichaCoche: (Int) -> Unit,
    onBorrarVehiculo: () -> Unit,
    ordenarPorPrecioState: Boolean,
    onOrdenarPorPrecio: () -> Unit,
    filtrarPorDescuentoState: Boolean,
    onFiltrarPorDescuento: () -> Unit
) {
    if (cocheSeleccionadoState != null) {
        BarraAppInferiorConVehiculoSeleccionado(
            onEditarFichaCoche = { onEditarFichaCoche(cocheSeleccionadoState.id) },
            onBorrarVehiculo = onBorrarVehiculo
        )
    } else {
        BarraAppInferiorSinVehiculoSeleccionado(
            ordenarPorPrecioState = ordenarPorPrecioState,
            onOrdenarPorPrecio = onOrdenarPorPrecio,
            filtrarPorDescuentoState = filtrarPorDescuentoState,
            onFiltrarPorDescuento = onFiltrarPorDescuento
        )
    }
}

@Composable
fun ListaCoches(
    modifier: Modifier = Modifier,
    cochesState: List<CocheUiState>,
    cocheSeleccionadoState: CocheUiState?,
    onSeleccionarCoche: (CocheUiState) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(
            items = cochesState
        ) { dato ->
            if (dato.id != cochesState.first().id)
                HorizontalDivider()
            ItemListaCoches(
                fichaCocheState = dato,
                seleccionadoState = dato.id == cocheSeleccionadoState?.id,
                onSeleccionarCoche = { onSeleccionarCoche(dato) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarGaleriaCoches(
    comportamientoAnteScroll: TopAppBarScrollBehavior
    = TopAppBarDefaults.pinnedScrollBehavior()
) = CenterAlignedTopAppBar(
    title = {
        Text(
            text = "Vehículos",
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    scrollBehavior = comportamientoAnteScroll
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleriaCochesScreen(
    cochesState: List<CocheUiState>,
    cocheSeleccionadoState: CocheUiState?,
    ordenarPorPrecioState: Boolean,
    filtrarPorDescuentoState: Boolean,
    onGaleriaCochesEvent: (GaleriaCochesEvent) -> Unit,
    onEditarFichaCoche: (id: Int) -> Unit
) {
    val comportamientoAnteScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            TopAppBarGaleriaCoches(comportamientoAnteScroll = comportamientoAnteScroll)
        },
        bottomBar = {
            BarraAppInferior(
                cocheSeleccionadoState = cocheSeleccionadoState,
                onEditarFichaCoche = onEditarFichaCoche,
                onBorrarVehiculo = { onGaleriaCochesEvent(GaleriaCochesEvent.OnBorrarVehiculo) },
                ordenarPorPrecioState = ordenarPorPrecioState,
                onOrdenarPorPrecio = { onGaleriaCochesEvent(GaleriaCochesEvent.OnOrdenarPorPrecio) },
                filtrarPorDescuentoState = filtrarPorDescuentoState,
                onFiltrarPorDescuento = { onGaleriaCochesEvent(GaleriaCochesEvent.OnFiltrarPorDescuento) }
            )
        },
        content = { innerPadding ->
            ListaCoches(
                modifier = Modifier.padding(innerPadding),
                cochesState = cochesState,
                cocheSeleccionadoState = cocheSeleccionadoState,
                onSeleccionarCoche = { onGaleriaCochesEvent(GaleriaCochesEvent.OnSeleccionarCoche(it)) }
            )
        }
    )
}
