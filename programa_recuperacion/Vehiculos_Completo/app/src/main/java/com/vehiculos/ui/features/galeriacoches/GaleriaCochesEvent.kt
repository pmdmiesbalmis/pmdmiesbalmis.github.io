package com.vehiculos.ui.features.galeriacoches

import com.vehiculos.ui.features.CocheUiState

sealed interface GaleriaCochesEvent {
    data class OnSeleccionarCoche(
        val coche: CocheUiState
    ) : GaleriaCochesEvent
    object OnBorrarVehiculo : GaleriaCochesEvent
    object OnOrdenarPorPrecio : GaleriaCochesEvent
    object OnFiltrarPorDescuento : GaleriaCochesEvent
}
