package com.pmdm.agenda.ui.composables

import android.telephony.PhoneNumberUtils
import android.util.Log
import android.util.Range
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.ValidacionCompuesta
import com.pmdm.agenda.utilities.validacion.ValidadorCompuesto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaCorreo
import com.pmdm.agenda.utilities.validacion.validadores.ValidaLongitudMaximaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaLongitudMinimaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidaNumeroEntero
import com.pmdm.agenda.utilities.validacion.validadores.ValidaTelefono
import com.pmdm.agenda.utilities.validacion.validadores.ValidaTextoVacio

@Composable
fun TextFieldWithErrorState(
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
    TextField(
        modifier = modifier,
        value = textoState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun OutlinedTextFieldWithErrorState(
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
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        keyboardActions = keyboardActions
    )
}

@Suppress("unused")
@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave",
    iconoInformativo: Painter = rememberVectorPainter(image = Icons.Filled.Lock),
) {
    var passwordHidden by remember { mutableStateOf(true) }
    TextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = iconoInformativo,
                contentDescription = label
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) labelShow else labelHide
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}

@Composable
fun OutlinedTextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave",
    iconoInformativo: Painter = rememberVectorPainter(image = Icons.Filled.Lock),
) {
    var passwordHidden by remember { mutableStateOf(true) }
    OutlinedTextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(
                painter = iconoInformativo,
                contentDescription = label
            )
        },
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) labelShow else labelHide
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}

@Suppress("unused")
@Composable
fun TextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    telefonoState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = telefonoState,
        textoPista = "999 99 99 99",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Teléfono"
            )
        },
        validacionState = validacionState,
        onValueChange = {
            var text = it
            if (!validacionState.hayError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {
                    Log.println(Log.ERROR, "Error", e.message.toString())
                }
            }
            onValueChange(text)
        }
    )
}

@Composable
fun OutlinedTextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    telefonoState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = telefonoState,
        textoPista = "999999999",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Teléfono"
            )
        },
        validacionState = validacionState,
        onValueChange = {
            var text = it
            if (!validacionState.hayError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {
                    Log.println(Log.ERROR, "Error", e.message.toString())
                }
            }
            onValueChange(text)
        }
    )
}

@Suppress("unused")
@Composable
fun TextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {

    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = emailState,
        textoPista = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email"
            )
        },
        validacionState = validacionState,
        onValueChange = { onValueChange(it) }
    )
}

@Composable
fun OutlinedTextFieldEmail(
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
        textoPista = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email"
            )
        },
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true)
@Composable
fun FiledTextCommonTest() {
    Column {
        var edadState by remember { mutableStateOf("") }
        var passwordState by remember { mutableStateOf("") }
        var aviso by remember { mutableStateOf("") }

        var validacionEdad by remember { mutableStateOf(Validacion(false)) }
        val validadorEdad = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacía"))
            .add(ValidaNumeroEntero(Range(0, 120), "La edad debe estar entre 0 y 120"))

        var nombreState by remember { mutableStateOf("") }
        var validacionNombre by remember { mutableStateOf(Validacion(false)) }
        val validadorNombre = ValidaLongitudMaximaTexto(20)

        var correoState by remember { mutableStateOf("") }
        var validacionCorreo by remember { mutableStateOf(Validacion(false)) }
        val validadorCorreo = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
            .add(ValidaCorreo("El correo no es válido"))

        var telefonoState by remember { mutableStateOf("") }
        var validacionTelefono by remember { mutableStateOf(Validacion(false)) }
        val validadorTelefono = ValidadorCompuesto(ValidaTextoVacio("No puede estar vacío"))
            .add(ValidaLongitudMinimaTexto(9, "El teléfono debe tener 9 caracteres"))
            .add(ValidaLongitudMaximaTexto(18, "El teléfono debe tener 18 caracteres"))
            .add(ValidaTelefono("El teléfono no es válido"))

        var validacionClave by remember { mutableStateOf(Validacion(false)) }
        val validadorClave = ValidaLongitudMinimaTexto(tamañoMinimo = 8)

        TextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Edad",
            textoState = edadState,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            validacionState = validacionEdad,
            onValueChange = {
                edadState = it
                validacionEdad = validadorEdad.valida(it)
                if (!validacionEdad.hayError)
                    edadState = it.toInt().toString()
            }
        )

        OutlinedTextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Nombre",
            textoState = nombreState,
            validacionState = validacionNombre,
            onValueChange = {
                nombreState = it
                validacionNombre = validadorNombre.valida(it)
            }
        )

        OutlinedTextFieldEmail(
            modifier = Modifier.fillMaxWidth(),
            emailState = correoState,
            validacionState = validacionCorreo,
            onValueChange = {
                correoState = it
                validacionCorreo = validadorCorreo.valida(it)
            })

        OutlinedTextFieldPhone(
            modifier = Modifier.fillMaxWidth(),
            telefonoState = telefonoState,
            validacionState = validacionTelefono,
            onValueChange = {
                telefonoState = it
                validacionTelefono = validadorTelefono.valida(it)
            }
        )

        OutlinedTextFieldPassword(
            modifier = Modifier.fillMaxWidth(),
            passwordState = passwordState,
            validacionState = validacionClave,
            onValueChange = {
                passwordState = it
                validacionClave = validadorClave.valida(it)
            }
        )

        Text(
            text = aviso,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                validacionEdad = validadorEdad.valida(edadState)
                validacionNombre = validadorNombre.valida(nombreState)
                validacionTelefono = validadorTelefono.valida(telefonoState)
                validacionCorreo = validadorCorreo.valida(correoState)
                validacionClave = validadorClave.valida(passwordState)
                aviso = if (ValidacionCompuesta()
                        .add(validacionEdad)
                        .add(validacionNombre)
                        .add(validacionTelefono)
                        .add(validacionCorreo)
                        .add(validacionClave)
                        .hayError
                )
                    "Hay errores"
                else ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Aceptar")
        }
    }
}
