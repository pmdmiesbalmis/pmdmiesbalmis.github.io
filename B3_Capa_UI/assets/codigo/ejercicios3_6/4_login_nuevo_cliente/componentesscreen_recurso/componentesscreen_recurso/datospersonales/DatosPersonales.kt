package com.pmdm.tienda.ui.features


import com.pmdm.tienda.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.pmdm.tienda.ui.composables.CircularImageFromResource
import com.pmdm.tienda.ui.composables.OutlinedTextFieldWithErrorState
import com.pmdm.tienda.ui.composables.TextWithLine
import com.pmdm.tienda.ui.features.newuser.datospersonales.DatosPersonalesEvent
import com.pmdm.tienda.ui.features.newuser.datospersonales.DatosPersonalesUiState
import com.pmdm.tienda.ui.features.newuser.datospersonales.ValidacionDatosPersonalesUiState

@Composable
fun DatosPersonales(
    datosPersonalesUIState: DatosPersonalesUiState,
    validadorDatosPersonalesUIState: ValidacionDatosPersonalesUiState,
    datosPersonalesEvent: (DatosPersonalesEvent) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            CircularImageFromResource(
                idImageResource = R.drawable.logearse,
                contentDescription = "Logearse"
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextWithLine(
                texto = "Datos Personales", color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Nombre",
                textoPista = "Introduce tu nombre y apellidos",
                textoState = datosPersonalesUIState.nombre,
                validacionState = validadorDatosPersonalesUIState.validacionNombre,
                onValueChange = { datosPersonalesEvent(DatosPersonalesEvent.NombreChanged(it)) },
            )

            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Dni",
                textoPista = "Introduce tu dni",
                textoState = datosPersonalesUIState.dni,
                validacionState = validadorDatosPersonalesUIState.validacionDni,
                onValueChange = { datosPersonalesEvent(DatosPersonalesEvent.DniChanged(it)) },
            )


            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Telèfono",
                textoPista = "Introduce un teléfono de contacto",
                textoState = datosPersonalesUIState.telefono,
                validacionState = validadorDatosPersonalesUIState.validacionTelefono,
                onValueChange = { datosPersonalesEvent(DatosPersonalesEvent.TelefonoChanged(it)) },
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { datosPersonalesEvent(DatosPersonalesEvent.OnClickSiguiente) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Siguiente")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatosPersonalesTest() {
    var datosPersonalesUIState by remember {
        mutableStateOf(DatosPersonalesUiState())
    }
    var validadorDatosPersonalesUIState
            by remember {
                mutableStateOf(ValidacionDatosPersonalesUiState())
            }

    DatosPersonales(
        datosPersonalesUIState = datosPersonalesUIState,
        validadorDatosPersonalesUIState = validadorDatosPersonalesUIState,
        {}
    )

}