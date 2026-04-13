package com.climatizacion.ui.features.casa

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.climatizacion.ui.features.TermostatoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasaScreen(
    termostatos: List<TermostatoUiState>,
    termostatoSeleccionado: TermostatoUiState?,
    filtroTermostatos: FiltroTermostatos,
    onCasaEvent: (CasaEvent) -> Unit
) {
    val comportamientoAnteScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
        },
        bottomBar = {
        },
        content = { innerPadding ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = "Contenido principal"
            )
        }
    )
}
