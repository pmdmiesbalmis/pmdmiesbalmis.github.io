package com.vehiculos.ui.features.dialogodescuento

import android.util.Range
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.pmdmiesbalmis.components.ui.composables.TextFieldWithErrorState
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorNumeroEntero
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio
import com.vehiculos.ui.theme.VehiculosTheme

@Composable
fun DescuentoDialog(
    porcentajeDescuento: Int,
    onAceptarCambios: (Int) -> Unit,
    onCancelarCambios: () -> Unit
) {
    val validadorPorcentaje = remember {
        ValidadorCompuesto<String>()
            .add(ValidadorTextoNoVacio(error = "El mínimo descuento es 0%"))
            .add(
                ValidadorNumeroEntero(
                    rango = Range(0, 99),
                    error = "El descuento debe estar entre 0% y 99%"
                )
            )
    }
    var textoPorcentaje by remember {
        mutableStateOf(porcentajeDescuento.toString())
    }
    val validacionPorcentaje : Validacion by remember {
        derivedStateOf {
            validadorPorcentaje.valida(textoPorcentaje)
        }
    }
    val onPorcentajeDescuentoChange = { nuevoValor: String ->
        textoPorcentaje = if (nuevoValor.toIntOrNull() != null) nuevoValor
                                   else ""
    }

    AlertDialog(
        onDismissRequest = {
            onCancelarCambios()
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Porcentaje descuento",
                textAlign = TextAlign.Center
            )
        },
        text = {
            TextFieldWithErrorState(
                label = "%",
                textoState = textoPorcentaje,
                textoPista = "Porcentaje de descuento",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                validacionState = validacionPorcentaje,
                onValueChange = onPorcentajeDescuentoChange
            )
        },
        confirmButton = {
            TextButton(onClick = {
                if (!validacionPorcentaje.hayError)
                    onAceptarCambios(textoPorcentaje.toInt())
            }) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelarCambios) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Preview
@Composable
fun DescuentoDialogPreview() {

    var porcentajeDescuentoState by remember {
        mutableIntStateOf(0)
    }
    val onAceptarCambios = { nuevoValor: Int ->
        porcentajeDescuentoState = nuevoValor
    }

    VehiculosTheme() {
        DescuentoDialog(
            porcentajeDescuento = porcentajeDescuentoState,
            onAceptarCambios = onAceptarCambios,
            onCancelarCambios = {}
        )
    }
}