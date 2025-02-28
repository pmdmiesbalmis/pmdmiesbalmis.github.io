package com.vehiculos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vehiculos.ui.features.fichacoche.FichaCocheScreen
import com.vehiculos.ui.features.fichacoche.FichaCocheViewModel
import kotlinx.serialization.Serializable

@Serializable
data class FichaCocheRoute(val id: Int? = null)

fun NavGraphBuilder.fichaCocheDestination(
    vm : FichaCocheViewModel,
    onNavigateTrasVerFicha: () -> Unit
) {
    composable<FichaCocheRoute> { backStackEntry ->
        vm.setCoche(backStackEntry.toRoute<FichaCocheRoute>().id)
        FichaCocheScreen(
            cocheState = vm.cocheState,
            verDialogoDescuentoState = vm.verDialogoDescuentoState,
            onVerDialogoDescuento = vm::onVerDialogoDescuento,
            onCancelarDialogoDescuento = vm::onCancelarDialogoDescuento,
            onAceptarDialogoDescuento = vm::onAceptarDialogoDescuento,
            onNavigateTrasVerFicha = onNavigateTrasVerFicha
        )
    }
}
