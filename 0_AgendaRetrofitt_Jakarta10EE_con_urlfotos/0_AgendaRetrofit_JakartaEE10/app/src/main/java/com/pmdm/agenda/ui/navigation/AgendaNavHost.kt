package com.pmdm.agenda.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pmdm.agenda.ui.features.formcontacto.ContactoViewModel
import com.pmdm.agenda.ui.features.vercontactos.ListaContactosViewModel

@Composable
fun AgendaNavHost() {
    val navController = rememberNavController()
    val vmLc = hiltViewModel<ListaContactosViewModel>()
    val vmFc = hiltViewModel<ContactoViewModel>()

    NavHost(
        navController = navController,
        startDestination = ListaContactosRoute
    ) {
        listaContactosDestination(
            vm = vmLc,
            onNavigateCrearContacto = {
                vmFc.clearContactoState()
                navController.navigate(FormContactoRoute())
            },
            onNavigateEditarContacto = { idContacto ->
                vmFc.clearContactoState()
                navController.navigate(FormContactoRoute(idContacto))
            }
        )
        formContactoDestination(
            vm = vmFc,
            onNavigateTrasFormContacto = { actualizaContactos ->
                navController.popBackStack()
                if (actualizaContactos) {
                    vmLc.cargaContactos()
                }
            }
        )
    }
}
