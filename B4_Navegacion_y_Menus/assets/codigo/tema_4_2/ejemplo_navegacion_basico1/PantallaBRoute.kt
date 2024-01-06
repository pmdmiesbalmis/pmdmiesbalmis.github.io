package com.pmdm.navegacion.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.pmdm.navegacion.ui.features.PantallaBScreen

const val PantallaBRoute = "pantalla_B"

fun NavController.navigateToPantallaB(
    navOptions: NavOptions? = null) {
    val ruta = "$PantallaBRoute"
    Log.d("Navegacion", "Navegando a $ruta")
    this.navigate(ruta, navOptions)
}

fun NavGraphBuilder.pantallaBScreen(
    onNavegarAtras: () -> Unit
) {
    composable(
        route = "$PantallaBRoute"
    ) { backStackEntry ->
        PantallaBScreen(
            onNavegarAtras = onNavegarAtras
        )
    }
}