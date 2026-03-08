package com.pmdm.agenda.ui.features.formcontacto

import androidx.compose.ui.graphics.ImageBitmap
import com.pmdm.agenda.ui.features.CatergoriaUiState

sealed interface ContactoEvent {
    data class OnChangeCategoria(val categoria: CatergoriaUiState) : ContactoEvent
    data class OnChangeNombre(val nombre: String) : ContactoEvent
    data class OnChangeApellidos(val apellidos: String) : ContactoEvent
    data class OnChangeCorreo(val correo: String) : ContactoEvent
    data class OnChangeTelefono(val telefono: String) : ContactoEvent
    data class OnChangeFoto(val foto: ImageBitmap) : ContactoEvent
    data class OnSaveContacto(val onNavigateTrasFormContacto: (actualizaContactos : Boolean) -> Unit) : ContactoEvent
    data object OnDismissError : ContactoEvent
}