package com.pmdm.tienda.ui.features.newuser.datospersonales

sealed interface   DatosPersonalesEvent {
    data class NombreChanged(val nombre:String): DatosPersonalesEvent
    data class DniChanged(val dni:String): DatosPersonalesEvent
    data class TelefonoChanged(val telefono:String): DatosPersonalesEvent
    object OnClickSiguiente:DatosPersonalesEvent
}