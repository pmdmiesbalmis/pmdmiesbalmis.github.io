package com.pmdm.navegacion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.navegacion.ui.features.PantallaAScreen

const val PantallaARoute = "pantalla_A"

fun NavGraphBuilder.pantallaAScreen(
    onNavigatePantallaB: () -> Unit
) {
    composable(
        route = "$PantallaARoute"
    ) { backStackEntry ->
         PantallaAScreen(onNavigatePantallaB)
    }
}