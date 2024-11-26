package com.pmdm.agenda.ui.features.formcontacto

import com.pmdm.agenda.ui.features.ContactoUiState
import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorCorreo
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorLongitudMaximaTexto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorLongitudMinimaTexto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTelefono
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio
import javax.inject.Inject

class ValidadorContacto @Inject constructor() : Validador<ContactoUiState> {
    val validadorNombre = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El nombre puede estar vacío"))
        .add(ValidadorLongitudMaximaTexto(20))
    val validadorApellidos = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("Los apellidos pueden estar vacíos"))
        .add(ValidadorLongitudMaximaTexto(20))
    val validadorCorreo = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El correo puede estar vacío"))
        .add(ValidadorCorreo("El correo no es válido"))
    val validadorTelefono = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El teléfono puede estar vacío"))
        .add(ValidadorLongitudMinimaTexto(9, "El teléfono debe tener 9 caracteres"))
        .add(ValidadorLongitudMaximaTexto(18, "El teléfono debe tener 18 caracteres"))
        .add(ValidadorTelefono("El teléfono no es válido"))

    override fun valida(contatoState : ContactoUiState): ValidacionContactoUiState {
        val validacionNombre = validadorNombre.valida(contatoState.nombre)
        val validacionApellidos = validadorApellidos.valida(contatoState.apellidos)
        val validacionCorreo = validadorCorreo.valida(contatoState.correo)
        val validacionTelefono = validadorTelefono.valida(contatoState.telefono)

        return ValidacionContactoUiState(
            validacionNombre = validacionNombre,
            validacionApellidos = validacionApellidos,
            validacionCorreo = validacionCorreo,
            validacionTelefono = validacionTelefono
        )
    }
}