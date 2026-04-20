package com.vehiculos.ui.features.galeriacoches

import com.vehiculos.models.Coche

sealed interface GaleriaCochesEvent {
    data class OnSeleccionarCoche(
        val coche: Coche
    ) : GaleriaCochesEvent
    object OnBorrarVehiculo : GaleriaCochesEvent
    object OnOrdenarPorPrecio : GaleriaCochesEvent
    object OnFiltrarPorDescuento : GaleriaCochesEvent
    object OnVerFichaCoche : GaleriaCochesEvent
}
