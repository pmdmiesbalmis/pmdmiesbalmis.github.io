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

    // Guadamos los callbacks con la navegación en los VM
    // para ser llamados desde los eventos. Abstrayendo así el VM de la navegación usada.
    vmGc.onNavegarFichaCoche = { id -> navController.navigate(FichaCocheRoute(id)) }
    vmFc.onVolverAtras = {
        navController.popBackStack()
        // Esta llamada se evitaría usando StateFlows
        // pues proporciona baja cohesión y evitaría
        // llamadas a la fuente de datos de forma innecesaria.
        vmGc.onActualizarCoches()
    }
    vmFc.onNavegarDialogoDescuento = { navController.navigate(DialogoDescuentoRoute) }

    NavHost(
        navController = navController,
        startDestination = GaleriaCochesRoute
    ) {
        dialogoDescuentoDestination(vm = vmFc)
        galeriaCochesDestination(vm = vmGc)
        fichaCocheDestination(vm = vmFc)
    }
}