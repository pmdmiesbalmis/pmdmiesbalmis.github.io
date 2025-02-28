package com.vehiculos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vehiculos.ui.features.galeriacoches.GaleriaCochesScreen
import com.vehiculos.ui.features.galeriacoches.GaleriaCochesViewModel
import kotlinx.serialization.Serializable

@Serializable
object GaleriaCochesRoute

fun NavGraphBuilder.galeriaCochesDestination(
    vm : GaleriaCochesViewModel,
    onEditarFichaCoche: (id: Int) -> Unit
) {
    composable<GaleriaCochesRoute> {
        GaleriaCochesScreen(
            cochesState = vm.cochesState,
            cocheSeleccionadoState = vm.cocheSeleccionadoState,
            ordenarPorPrecioState = vm.ordenarPorPrecioState,
            filtrarPorDescuentoState = vm.filtrarPorDescuentoState,
            onGaleriaCochesEvent = vm::onGaleriaCochesEvent,
            onEditarFichaCoche = onEditarFichaCoche
        )
    }
}
