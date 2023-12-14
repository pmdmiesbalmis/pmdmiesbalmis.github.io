package com.pmdm.tienda.ui.features.newuser.datospersonales

import com.pmdm.utilities.validacion.Validacion


data class ValidacionDatosPersonalesUiState(
    val validacionDni: Validacion = Validacion(false),
    val validacionNombre: Validacion = Validacion(false),
    val validacionTelefono: Validacion = Validacion(false),
    val validacionDatosPersonales: Validacion = Validacion(false)
)