package com.pmdm.agenda.models

import java.util.EnumSet

data class Contacto(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val urlFoto: String?,
    val correo: String,
    val telefono: String,
    val categorias: EnumSet<Categorias>
) {
    enum class Categorias {
        Amigos, Trabajo, Familia, Emergencias
    }
}