package com.holamundo.ui.features.ejemplos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holamundo.ui.theme.HolaMundoTheme

enum class ContenidoCaja { BOTON, DESPEDIDA_TRISTE, DESPEDIDA_ALEGRE }

@Composable
fun ContenidoCaja(
    contenidoCajaState: ContenidoCaja,
    onClickVerOferta: () -> Unit
) {
    when (contenidoCajaState) {
        ContenidoCaja.BOTON -> {
            Button(onClick = onClickVerOferta) {
                Text(text = "Ver Oferta")
            }
        }

        ContenidoCaja.DESPEDIDA_TRISTE -> {
            Text(text = "Adios, tu te lo pierdes")
        }

        ContenidoCaja.DESPEDIDA_ALEGRE -> {
            Text(text = "Enhorabuena, excelente elección")
        }
    }
}

@Composable
fun DialogoOferta(
    onAceptarDialogoOferta: () -> Unit,
    onRechazarDialogoOferta: () -> Unit,
    onCalcelaDialogoOferta: () -> Unit
) =
    AlertDialog(
        icon = {
            Icon(
                Icons.Filled.QuestionAnswer,
                contentDescription = "Pregunta"
            )
        },
        title = { Text(text = "Oferta") },
        text = {
            val ofertas = remember {
                listOf(
                    "Apartamento en Torrevieja (Alicante)",
                    "Aston Martin DB9",
                    "100 Ceniceros del IES Balmis"
                )
            }
            Text(text = ofertas.random())
        },
        onDismissRequest = onCalcelaDialogoOferta,
        confirmButton = {
            TextButton(onClick = {
                onAceptarDialogoOferta()
                onCalcelaDialogoOferta()
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onRechazarDialogoOferta()
                onCalcelaDialogoOferta()
            }) {
                Text("Rechazar")
            }
        }
    )


@Composable
fun BoxOferta() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .size(height = 300.dp, width = 0.dp),
    contentAlignment = Alignment.Center
) {
    var verDialogoOferta by remember { mutableStateOf(false) }
    var contenidoCaja by remember { mutableStateOf(ContenidoCaja.BOTON) }

    ContenidoCaja(
        contenidoCajaState = contenidoCaja,
        onClickVerOferta = { verDialogoOferta = true }
    )

    if (verDialogoOferta) {
        DialogoOferta(
            onAceptarDialogoOferta = {
                contenidoCaja = ContenidoCaja.DESPEDIDA_ALEGRE
            },
            onRechazarDialogoOferta = {
                contenidoCaja = ContenidoCaja.DESPEDIDA_TRISTE
            },
        ) {
            verDialogoOferta = false
        }
    }
}

@Preview(showBackground = true, name = "EjemploAlertDialogPreview")
@Composable
fun EjemploAlertDialogPreview() {
    HolaMundoTheme {
        BoxOferta()
    }
}