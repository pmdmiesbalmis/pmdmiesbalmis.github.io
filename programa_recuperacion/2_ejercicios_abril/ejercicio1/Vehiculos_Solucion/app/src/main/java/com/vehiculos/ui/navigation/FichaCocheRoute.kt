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
    vm : FichaCocheViewModel
) {
    composable<FichaCocheRoute> { backStackEntry ->
        vm.setCoche(backStackEntry.toRoute<FichaCocheRoute>().id)
        vm.cocheState?.let { c ->
            FichaCocheScreen(
                cocheState = c,
                onFichaCocheEvent = vm::onFichaCocheEvent
            )
        }
    }
}
