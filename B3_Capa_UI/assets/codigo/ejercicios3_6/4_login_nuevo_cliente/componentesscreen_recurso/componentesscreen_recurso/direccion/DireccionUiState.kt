package com.pmdm.tienda.ui.features.newuser.direccion

import com.pmdm.utilities.validacion.Validacion

data class DireccionUiState(
    val calle: String,
    val ciudad: String,
    val codigoPostal: String,
    ) {
    constructor() : this(
        calle = "",
        ciudad = "",
        codigoPostal = ""
    )
}