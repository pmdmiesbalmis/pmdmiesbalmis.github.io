package com.pmdm.agenda.ui.features.vercontactos


import androidx.compose.ui.graphics.ImageBitmap
import com.pmdm.agenda.models.Contacto
import com.pmdm.agenda.utilities.imagenes.Imagenes
import java.time.Instant

data class ContactoUiState(
    val id: Int = Instant.now().epochSecond.toInt(),
    val nombre: String = "",
    val apellidos: String = "",
    val foto: ImageBitmap? = null,
    val correo: String = "",
    val telefono: String = "",
    val categorias : CatergoriaUiState = CatergoriaUiState(),
)

fun ContactoUiState.toContacto() = Contacto(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    correo = correo,
    telefono = telefono,
    foto = foto?.let { Imagenes.bitmapToBase64(it) },
    categorias = categorias.toEnum()
)

fun Contacto.toContactoUiState() = ContactoUiState(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    correo = correo,
    telefono = telefono,
    foto = foto?.let { Imagenes.base64ToBitmap(it) },
    categorias = CatergoriaUiState(
        amigos = categorias.contains(Contacto.Categorias.Amigos),
        trabajo = categorias.contains(Contacto.Categorias.Trabajo),
        familia = categorias.contains(Contacto.Categorias.Familia),
        emergencias = categorias.contains(Contacto.Categorias.Emergencias))
)