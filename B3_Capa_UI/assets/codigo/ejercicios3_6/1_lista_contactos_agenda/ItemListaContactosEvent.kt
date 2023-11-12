package com.pmdm.agenda.ui.features.vercontactos

sealed class ItemListaContactosEvent {
    data class OnClickContacto(val contacto : ContactoUiState) : ItemListaContactosEvent()
    object OnDeleteContacto : ItemListaContactosEvent()
}