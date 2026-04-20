package com.climatizacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.climatizacion.ui.features.casa.CasaViewModel
import com.climatizacion.ui.features.termostato.TermostatoViewModel

@Composable
fun ClimatizacionNavHost() {
    val navController = rememberNavController()
    val vmCasa = hiltViewModel<CasaViewModel>()
    val vmTermostato = hiltViewModel<TermostatoViewModel>()

    NavHost(
        navController = navController,
        startDestination = TermostatosCasaGraphRoute
    ) {
        termostatosCasaScreen(
            vm = vmCasa,
            onNavigateConfigurarTermostato = { id -> navController.navigateToEditarTermostato(id) }
        )
        fichaCocheScreen(
            vm = vmTermostato,
            onNavigateTrasEditarTermostato =
            {
                vmCasa.ActualizaTermostatos()
                navController.navigateUp()
            }
        )
    }
}