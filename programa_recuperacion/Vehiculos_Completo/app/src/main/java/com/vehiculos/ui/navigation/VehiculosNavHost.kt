package com.vehiculos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vehiculos.ui.features.fichacoche.FichaCocheViewModel
import com.vehiculos.ui.features.galeriacoches.GaleriaCochesViewModel

@Composable
fun VehiculosNavHost() {
    val navController = rememberNavController()
    val vmGc = hiltViewModel<GaleriaCochesViewModel>()
    val vmFc = hiltViewModel<FichaCocheViewModel>()

    NavHost(
        navController = navController,
        startDestination = GaleriaCochesRoute
    ) {
        galeriaCochesDestination(
            vm = vmGc,
            onEditarFichaCoche = { id -> navController.navigate(FichaCocheRoute(id)) }
        )
        fichaCocheDestination(
            vm = vmFc,
            onNavigateTrasVerFicha =
            {
                vmGc.onActualizarCoches()
                navController.popBackStack()
            }
        )
    }
}