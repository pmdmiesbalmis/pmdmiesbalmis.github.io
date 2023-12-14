package com.pmdm.tienda.ui.features.newuser.direccion

import com.pmdm.utilities.validacion.Validacion

data class ValidacionDireccionUiState(
    val validacionCalle: Validacion = Validacion(false),
    val validacionCiudad: Validacion = Validacion(false),
    val validacionCodigoPostal: Validacion = Validacion(false),
    val validacionDireccion: Validacion = Validacion(false)
)
