package com.pmdm.tienda.ui.features.newuser.datospersonales

import com.pmdm.utilities.validacion.Validacion

data class DatosPersonalesUiState(
    val dni: String,
    val nombre: String,
    val telefono: String,
)
{
    constructor():this(
        dni = "",
        nombre = "",
        telefono=""
    )
}


