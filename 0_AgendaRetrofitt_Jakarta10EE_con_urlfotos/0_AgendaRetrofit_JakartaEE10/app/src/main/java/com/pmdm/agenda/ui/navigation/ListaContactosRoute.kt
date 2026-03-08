package com.pmdm.agenda.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmdm.agenda.ui.features.vercontactos.ItemListaContactosEvent
import com.pmdm.agenda.ui.features.vercontactos.ListaContactosScreen
import com.pmdm.agenda.ui.features.vercontactos.ListaContactosViewModel
import kotlinx.serialization.Serializable

@Serializable
object ListaContactosRoute

fun NavGraphBuilder.listaContactosDestination(
    vm : ListaContactosViewModel,
    onNavigateCrearContacto: () -> Unit,
    onNavigateEditarContacto: (idContacto: Int) -> Unit
) {
    composable<ListaContactosRoute> {
        ListaContactosScreen(
            contactosState = vm.contactosState,
            contactoSeleccionadoState = vm.contatoSleccionadoState,
            filtradoActivoState = vm.filtradoActivoState,
            filtroCategoriaState = vm.filtroCategoriaState,
            informacionEstadoState = vm.informacionEstadoState,
            onActualizaContactos =  { vm.cargaContactos() },
            onActivarFiltradoClicked = { vm.onActivarFiltradoClicked() },
            onFiltroModificado = { categorias -> vm.onFiltroModificado(categorias) },
            onContactoClicked = { c ->
                vm.onItemListaContatoEvent(ItemListaContactosEvent.OnClickContacto(c))
            },
            onAddClicked = {
                vm.onItemListaContatoEvent(
                    ItemListaContactosEvent.OnCrearContacto(
                        onNavigateCrearContacto
                    )
                )
            },
            onEditClicked = {
                vm.onItemListaContatoEvent(
                    ItemListaContactosEvent.OnEditContacto(
                        onNavigateEditarContacto
                    )
                )
            },
            onDeleteClicked = {
                vm.onItemListaContatoEvent(ItemListaContactosEvent.OnDeleteContacto)
            }
        )
    }
}