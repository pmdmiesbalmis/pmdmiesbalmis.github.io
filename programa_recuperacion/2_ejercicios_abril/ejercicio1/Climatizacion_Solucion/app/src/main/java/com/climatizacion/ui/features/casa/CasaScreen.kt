package com.climatizacion.ui.features.casa

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.climatizacion.ui.features.TermostatoUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarTermostatos(
    comportamientoAnteScroll: TopAppBarScrollBehavior
    = TopAppBarDefaults.pinnedScrollBehavior()
) = CenterAlignedTopAppBar(
    title = {
        Text(
            text = "Termostatos",
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    scrollBehavior = comportamientoAnteScroll
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridTermostatos(
    modifier: Modifier = Modifier,
    termostatos: List<TermostatoUiState>,
    termostatoSeleccionado: TermostatoUiState?,
    onSeleccionarTermostato: (TermostatoUiState) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(140.dp),
        state = rememberLazyGridState()
    ) {
        items(
            count = termostatos.size,
            key = { index -> termostatos[index].id }
        ) { index ->
            TermostatoThumb(
                seleccionado = termostatoSeleccionado?.id == termostatos[index].id,
                termostatoUiState = termostatos[index],
                onClickAction = { onSeleccionarTermostato(termostatos[index]) },
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
private fun BarraAppInferiorConTermostatoSeleccionado(
    onConfigurarTermostato: () -> Unit,
    onBorrarTermostato: () -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(
                onClick = onConfigurarTermostato
            ) {
                Icon(
                    imageVector = Icons.Filled.Build,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Configurar Termostato"
                )
            }
            IconButton(
                onClick = onBorrarTermostato
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Borrar Termostato"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BarraAppInferiorSinTermostatoSeleccionado(
    filtroTermostatos: FiltroTermostatos,
    onOpcionDeFiltrado: (FiltroTermostatos) -> Unit
) {
    BottomAppBar(
        actions = {
            SingleChoiceSegmentedButtonRow(
               space = -20.dp
            ) {
                FiltroTermostatos.entries.forEach { filtro ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.baseShape,
                        selected = filtro == filtroTermostatos,
                        onClick = { onOpcionDeFiltrado(filtro) }
                    ) {
                        Text(filtro.label)
                    }
                }
            }
        }
    )
}

@Composable
fun BarraAppInferior(
    termostatoSeleccionado: Boolean,
    onConfigurarTermostato: () -> Unit,
    onBorrarTermostato: () -> Unit,
    filtroTermostatos: FiltroTermostatos,
    onOpcionDeFiltrado: (FiltroTermostatos) -> Unit
) {
    if (termostatoSeleccionado) {
        BarraAppInferiorConTermostatoSeleccionado(
            onConfigurarTermostato = onConfigurarTermostato,
            onBorrarTermostato = onBorrarTermostato
        )

    } else {
        BarraAppInferiorSinTermostatoSeleccionado(
            filtroTermostatos = filtroTermostatos,
            onOpcionDeFiltrado = onOpcionDeFiltrado
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasaScreen(
    termostatos: List<TermostatoUiState>,
    termostatoSeleccionado: TermostatoUiState?,
    filtroTermostatos: FiltroTermostatos,
    onNavigateConfigurarTermostato: (Int) -> Unit,
    onCasaEvent: (CasaEvent) -> Unit
) {
    val comportamientoAnteScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            TopAppBarTermostatos(
                comportamientoAnteScroll = comportamientoAnteScroll
            )
        },
        bottomBar = {
            BarraAppInferior(
                termostatoSeleccionado = termostatoSeleccionado != null,
                onConfigurarTermostato = {
                    onCasaEvent(CasaEvent.onConfigurarTermostato(onNavigateConfigurarTermostato))
                },
                onBorrarTermostato = { onCasaEvent(CasaEvent.onBorrarTermostato) },
                filtroTermostatos = filtroTermostatos,
                onOpcionDeFiltrado = { onCasaEvent(CasaEvent.onOpcionDeFiltrado(it)) }
            )
        },
        content = { innerPadding ->
            GridTermostatos(
                modifier = Modifier.padding(innerPadding),
                termostatos = termostatos,
                termostatoSeleccionado = termostatoSeleccionado,
                onSeleccionarTermostato = { onCasaEvent(CasaEvent.OnSeleccionarTermostato(it)) }
            )
        }
    )
}
