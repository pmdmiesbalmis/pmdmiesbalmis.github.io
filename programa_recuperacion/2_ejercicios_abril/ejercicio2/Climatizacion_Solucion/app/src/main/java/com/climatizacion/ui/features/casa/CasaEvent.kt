package com.climatizacion.ui.features.casa

import com.climatizacion.ui.features.TermostatoUiState

sealed interface CasaEvent {
    data class onConfigurarTermostato(
        val onNavigateConfigurarTermostato: (idTermostato: Int) -> Unit
    ) : CasaEvent
    data class OnSeleccionarTermostato(
        val termostato: TermostatoUiState
    ) : CasaEvent
    data class onOpcionDeFiltrado(
        val filtro: FiltroTermostatos
    ) : CasaEvent
    object onBorrarTermostato : CasaEvent
}