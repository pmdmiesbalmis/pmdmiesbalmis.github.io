package com.vehiculos.ui.features.dialogodescuento

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.vehiculos.ui.theme.VehiculosTheme

@Composable
fun DescuentoDialog(
    porcentajeDescuento: Int,
    onAceptarCambios: (Int) -> Unit,
    onCancelarCambios: () -> Unit
) {
    // TODO : Definir diálogo según las especificaciones.
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