package com.holamundo.ui.features.contador

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContadorStateful() {
    var cuentaState by remember { mutableStateOf(0) }
    val onAumentarCuenta: () -> Unit = { cuentaState++ }

    ContadorStateless(
        cuentaState = cuentaState,
        onAumentarCuenta = onAumentarCuenta
    )
}

@Composable
fun ContadorStateless(
    cuentaState: Int,
    onAumentarCuenta: () -> Unit) =
    Column {
        Button(onClick = onAumentarCuenta) {
            Text("Cuenta Clicks")
        }
        Text(text = "Llevas $cuentaState Clicks")
    }

@Preview(showBackground = true, name = "ContadorScreenPreview")
@Composable
fun ContadorScreenPreview() = ContadorStateful()

