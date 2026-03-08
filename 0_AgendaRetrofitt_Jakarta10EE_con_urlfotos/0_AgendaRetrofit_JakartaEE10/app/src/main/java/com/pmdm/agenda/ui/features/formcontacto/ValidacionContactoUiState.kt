package com.pmdm.agenda.ui.features.formcontacto

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.ValidacionCompuesta

data class ValidacionContactoUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionApellidos: Validacion = object : Validacion {},
    val validacionCorreo: Validacion = object : Validacion {},
    val validacionTelefono: Validacion = object : Validacion {}
) : Validacion {
    private var validacionCompuesta: ValidacionCompuesta? = null

    private fun componerValidacion(): ValidacionCompuesta {
        validacionCompuesta = ValidacionCompuesta()
            .add(validacionNombre)
            .add(validacionApellidos)
            .add(validacionCorreo)
            .add(validacionTelefono)
        return validacionCompuesta!!
    }

    // Puesto que la validación se compone de otras validaciones,
    // delegamos en la validación compuesta. Que calculamos en el momento del acceso.
    override val hayError: Boolean
        get() = validacionCompuesta?.hayError ?: componerValidacion().hayError
    override val mensajeError: String?
        //        get() = "Revisa los datos del contacto"
        get() = validacionCompuesta?.mensajeError ?: componerValidacion().mensajeError
}
