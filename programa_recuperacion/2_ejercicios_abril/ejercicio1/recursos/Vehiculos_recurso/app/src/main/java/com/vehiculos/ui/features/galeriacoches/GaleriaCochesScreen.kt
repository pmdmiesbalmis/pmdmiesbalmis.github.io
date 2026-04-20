@file:OptIn(ExperimentalMaterial3Api::class)

package com.vehiculos.ui.features.galeriacoches

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.vehiculos.models.Coche

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleriaCochesScreen(
    cochesState: List<Coche>,
    cocheSeleccionadoState: Coche?,
    ordenarPorPrecioState: Boolean,
    filtrarPorDescuentoState: Boolean,
    onGaleriaCochesEvent: (GaleriaCochesEvent) -> Unit,
) {
    // TODO: Añadir Scaffold con TopAppBar y BottomAppBar
}

@Preview(showBackground = true)
@Composable
fun GaleriaCochesScreenPreview() {
    val coches = listOf(
        Coche(1, "Toyota", "Corolla", 2021, 18000f, 10, "Sedán compacto eficiente", null),
        Coche(2, "BMW", "Serie 3", 2022, 45000f, 5, "Berlina de lujo deportiva", null),
        Coche(3, "Ford", "Focus", 2020, 15000f, 15, "Compacto versátil", null),
    )
    val cocheSeleccionado by remember { mutableStateOf<Coche?>(coches[0]) }
    GaleriaCochesScreen(
        cochesState = coches,
        cocheSeleccionadoState = cocheSeleccionado,
        ordenarPorPrecioState = false,
        filtrarPorDescuentoState = true,
        onGaleriaCochesEvent = {}
    )
}

