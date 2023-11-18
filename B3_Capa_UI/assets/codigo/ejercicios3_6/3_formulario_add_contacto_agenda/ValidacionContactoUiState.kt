package com.pmdm.agenda.ui.features.formcontacto

import com.pmdm.agenda.utilities.validacion.Validacion

data class ValidacionContactoUiState(
    val validacionNombre: Validacion = Validacion(false),
    val validacionApellidos: Validacion = Validacion(false),
    val validacionCorreo: Validacion = Validacion(false),
    val validacionTelefono: Validacion = Validacion(false),
    val validacionContacto: Validacion = Validacion(false)
)
