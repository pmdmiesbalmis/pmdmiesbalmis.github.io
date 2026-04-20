package com.vehiculos.ui.features.fichacoche

sealed interface FichaCochesEvent {
    object OnCambiarOferta : FichaCochesEvent
    object OnVolverAGaleria : FichaCochesEvent
    data class OnAceptarDialogoDescuento(val porcentajeDescuento: Int) : FichaCochesEvent
    object OnCancelarDialogoDescuento: FichaCochesEvent
}
