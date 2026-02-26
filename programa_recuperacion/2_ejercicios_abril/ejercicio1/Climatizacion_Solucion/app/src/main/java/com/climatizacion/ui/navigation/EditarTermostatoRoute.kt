package com.climatizacion.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.climatizacion.ui.features.termostato.TermostatoScreen
import com.climatizacion.ui.features.termostato.TermostatoViewModel

const val EditarTermostatoGraphRoute = "editar_termostato"
private const val TermostatoParameterName = "idTermostato"

fun NavController.navigateToEditarTermostato(
    idCoche: Int,
    navOptions: NavOptions? = null) {
    this.navigate("$EditarTermostatoGraphRoute/$idCoche", navOptions)
}

fun NavGraphBuilder.fichaCocheScreen(
    vm : TermostatoViewModel,
    onNavigateTrasEditarTermostato: () -> Unit
) {
    composable(
        route = "$EditarTermostatoGraphRoute/{$TermostatoParameterName}",
        arguments = listOf(
            navArgument(TermostatoParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val id :Int? = backStackEntry.arguments?.getInt(TermostatoParameterName, -1)
        if (id != null
            && vm.termostatoUiState?.id != id) {
            vm.setTermostato(id)
        }
        TermostatoScreen(
            termostatoUiState = vm.termostatoUiState!!,
            onTermostatoEvent = vm::onTermostatoEvent,
            onNavigateTrasEditarTermostato = onNavigateTrasEditarTermostato
        )
    }
}
