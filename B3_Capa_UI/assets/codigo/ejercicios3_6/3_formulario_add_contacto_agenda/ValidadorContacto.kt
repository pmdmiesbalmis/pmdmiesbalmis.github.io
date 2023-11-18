package com.pmdm.agenda.ui.features.formcontacto

import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.ValidacionCompuesta
import com.pmdm.agenda.utilities.validacion.ValidadorCompuesto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaCorreo
import com.pmdm.agenda.utilities.validacion.validadores.ValidaLongitudMaximaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaLongitudMinimaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaTelefono
import com.pmdm.agenda.utilities.validacion.validadores.ValidaTextoVacio
import javax.inject.Inject

class ValidadorContacto @Inject constructor() {
    val validadorNombre = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
        .add(ValidaLongitudMaximaTexto(20))
    val validadorApellidos = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
        .add(ValidaLongitudMaximaTexto(20))
    val validadorCorreo = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
        .add(ValidaCorreo("El correo no es válido"))
    val validadorTelefono = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
        .add(ValidaLongitudMinimaTexto(9, "El teléfono debe tener 9 caracteres"))
        .add(ValidaLongitudMaximaTexto(18, "El teléfono debe tener 18 caracteres"))
        .add(ValidaTelefono("El teléfono no es válido"))

    fun valida(contatoState : ContactoUiState): ValidacionContactoUiState {
        val validacionNombre = validadorNombre.valida(contatoState.nombre)
        val validacionApellidos = validadorApellidos.valida(contatoState.apellidos)
        val validacionCorreo = validadorCorreo.valida(contatoState.correo)
        val validacionTelefono = validadorTelefono.valida(contatoState.telefono)
        val validacionContacto = if (ValidacionCompuesta()
                .add(validacionNombre)
                .add(validacionApellidos)
                .add(validacionCorreo)
                .add(validacionTelefono)
                .hayError
        )
            Validacion(true, "* Revisa los errores")
        else
            Validacion(false)

        return ValidacionContactoUiState(
            validacionNombre = validacionNombre,
            validacionApellidos = validacionApellidos,
            validacionCorreo = validacionCorreo,
            validacionTelefono = validacionTelefono,
            validacionContacto = validacionContacto
        )
    }
}