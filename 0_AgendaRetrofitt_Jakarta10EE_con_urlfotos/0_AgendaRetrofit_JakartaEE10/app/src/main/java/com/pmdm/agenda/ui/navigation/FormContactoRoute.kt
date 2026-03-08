package com.pmdm.agenda.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pmdm.agenda.ui.features.formcontacto.ContactoViewModel
import com.pmdm.agenda.ui.features.formcontacto.FormContactoScreen
import kotlinx.serialization.Serializable

@Serializable
data class FormContactoRoute(val id: Int? = null)

fun NavGraphBuilder.formContactoDestination(
    vm : ContactoViewModel,
    onNavigateTrasFormContacto: (actualizaContactos : Boolean) -> Unit
) {
    composable<FormContactoRoute> { backStackEntry ->
        vm.setContactoState(backStackEntry.toRoute<FormContactoRoute>().id)
        FormContactoScreen(
            contactoState = vm.contactoState,
            validacionContactoState = vm.validacionContactoState,
            informacionEstado = vm.informacionEstadoState,
            onContactoEvent = vm::onContactoEvent,
            onNavigateTrasFormContacto = onNavigateTrasFormContacto
        )
    }
}