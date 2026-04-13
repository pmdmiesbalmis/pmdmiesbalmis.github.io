package com.climatizacion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.climatizacion.ui.features.TermostatoUiState
import com.climatizacion.ui.features.casa.CasaEvent
import com.climatizacion.ui.features.casa.CasaScreen
import com.climatizacion.ui.features.casa.CasaViewModel
import com.climatizacion.ui.features.casa.FiltroTermostatos

const val TermostatosCasaGraphRoute = "termostatos_casa"

fun NavGraphBuilder.termostatosCasaScreen(
    vm : CasaViewModel,
    onNavigateConfigurarTermostato: (Int) -> Unit
) {
    composable(
        route = TermostatosCasaGraphRoute,
        arguments = emptyList()
    ) {
        CasaScreen(
            termostatos = vm.termostatos,
            termostatoSeleccionado = vm.termostatoSeleccionadoState,
            filtroTermostatos = vm.filtro,
            onNavigateConfigurarTermostato = onNavigateConfigurarTermostato,
            vm :: onCasaEvent
        )
    }
}
