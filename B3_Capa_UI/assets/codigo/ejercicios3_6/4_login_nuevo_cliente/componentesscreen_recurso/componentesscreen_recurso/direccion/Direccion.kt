package com.pmdm.tienda.ui.features


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pmdm.tienda.ui.composables.CircularImageFromResource
import com.pmdm.tienda.ui.composables.OutlinedTextFieldWithErrorState
import com.pmdm.tienda.ui.composables.TextWithLine
import com.pmdm.tienda.R
import com.pmdm.tienda.ui.features.newuser.direccion.DireccionEvent
import com.pmdm.tienda.ui.features.newuser.direccion.DireccionUiState
import com.pmdm.tienda.ui.features.newuser.direccion.ValidacionDireccionUiState


@Composable
fun Direccion(
    direccionUiState: DireccionUiState,
    validadorDireccionUiState: ValidacionDireccionUiState,
    direccionEvent: (DireccionEvent)->Unit
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
            CircularImageFromResource(idImageResource = R.drawable.logearse , contentDescription = "Logearse")
            Spacer(modifier = Modifier.height(20.dp))

            TextWithLine(
                texto = "Dirección", color =MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Dirección",
                textoPista = "Introduce calle, número y puerta",
                textoState = direccionUiState.calle,
                validacionState = validadorDireccionUiState.validacionCalle,
                onValueChange = { direccionEvent(DireccionEvent.CalleChanged(it)) },
            )

            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Ciudad",
                textoPista = "Introduce la ciudad",
                textoState = direccionUiState.ciudad,
                validacionState = validadorDireccionUiState.validacionCiudad,
                onValueChange = { direccionEvent(DireccionEvent.CiudadChanged(it)) },
            )
            OutlinedTextFieldWithErrorState(
                modifier = Modifier.fillMaxWidth(),
                label = "Codigo Postal",
                textoPista = "Introduce el código postal",
                textoState = direccionUiState.codigoPostal,
                validacionState = validadorDireccionUiState.validacionCodigoPostal,
                onValueChange = { direccionEvent(DireccionEvent.CodigoPostalChanged(it)) },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { direccionEvent(DireccionEvent.OnClickSiguiente) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Siguiente")
            }
        }
    }
}