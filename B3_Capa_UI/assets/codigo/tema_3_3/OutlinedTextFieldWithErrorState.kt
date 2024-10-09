package com.pmdm.proyectobase.ui.features.tema33

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

private interface Validacion {
    val hayError: Boolean      // Si hay error o no.
        get() = false
    val mensajeError: String? // EL mensaje asociado al error.
        get() = null
}

@Composable
private fun OutlinedTextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textoState: String,
    textoPista: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validacionState: Validacion,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textoState,
        onValueChange = onValueChange,
        singleLine = true,
        // Icono al principio del TextField por defecto vale null.
        leadingIcon = leadingIcon,
        // Como vamos a mostrar el texto de la pista o hint cuando estemos editando.
        // por defecto la pista es la cadena vacía.
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(
                    color = MaterialTheme.colorScheme
                        .onSurfaceVariant.copy(alpha = 0.4f)
                )
            )
        },
        // Etiqueta personalizada que se muestra cuando no hay texto o
        // encima del TextField cuando estamos editando.
        // Le ponemos un asterisco si hay error como hemos especificado.
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        // La opciones del teclado permitirán la entrada alfanumérica.
        keyboardOptions = keyboardOptions,
        // Composable bajo el TextField que se muestra cuando hay error.
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        // Parámetro con el estado del error.
        isError = validacionState.hayError,
        keyboardActions = keyboardActions
    )
}

@Composable
private fun OutlinedTextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = emailState,
        // La pista cambiará.
        textoPista = "ejemplo@correo.com",
        // Las opciones de teclado serán para un email.
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        // El icono será el de un email.
        leadingIcon = {
            Icon(
                painter = Filled.getMailIcon(),
                contentDescription = "Email"
            )
        },
        // Será Stateles y la forma de validar la decidiremos al usarlo.
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@PreviewLightDark
@Composable
private fun TextFiledPreview() {
    var nombreState by remember { mutableStateOf("") }
    var validacionNombre by remember { mutableStateOf(object : Validacion {} as Validacion) }

    var emailState by remember { mutableStateOf("") }
    var validacionEmail by remember { mutableStateOf(object : Validacion {} as Validacion) }

    ProyectoBaseTheme {
        Surface {
            Column {
                OutlinedTextFieldWithErrorState(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Nombre",
                    textoState = nombreState,
                    validacionState = validacionNombre,
                    onValueChange = {
                        nombreState = it
                        validacionNombre = object : Validacion {
                            override val hayError: Boolean
                                get() = it.isEmpty()
                            override val mensajeError: String
                                get() = "El nombre no puede estar vacío"
                        }
                    }
                )
                OutlinedTextFieldEmail(
                    modifier = Modifier.fillMaxWidth(),
                    emailState = emailState,
                    validacionState = validacionEmail,
                    onValueChange = {
                        emailState = it
                        validacionEmail = object : Validacion {
                            override val hayError: Boolean
                                get() = it.isEmpty()
                                        || !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$")
                                    .matches(it)
                            override val mensajeError: String
                                get() = "El email no es válido"
                        }
                    }
                )
            }
        }
    }
}
