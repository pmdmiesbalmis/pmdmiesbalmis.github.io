package com.pmdm.navegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavHostEjemploBasico(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PantallaARoute
    ) {
        pantallaAScreen(
            onNavigatePantallaB = {
                navController.navigateToPantallaB()
            }
        )
        pantallaBScreen(
            onNavegarAtras = {
                navController.navigateUp()
            }
        )
    }
}