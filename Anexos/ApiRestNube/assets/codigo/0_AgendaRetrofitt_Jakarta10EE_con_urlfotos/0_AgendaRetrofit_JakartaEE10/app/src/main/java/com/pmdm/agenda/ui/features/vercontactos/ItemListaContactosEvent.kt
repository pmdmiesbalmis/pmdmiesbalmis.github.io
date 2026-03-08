package com.pmdm.agenda.ui.features.vercontactos

import com.pmdm.agenda.ui.features.ContactoUiState

sealed class ItemListaContactosEvent {
    data class OnClickContacto(val contacto : ContactoUiState)
        : ItemListaContactosEvent()
    data class OnCrearContacto(
        val onNavigateCrearContacto: () -> Unit
    ) : ItemListaContactosEvent()
    data class OnEditContacto(
        val onNavigateEditarContacto: (idContacto: Int) -> Unit
    ) : ItemListaContactosEvent()
    object OnDeleteContacto
        : ItemListaContactosEvent()
}
