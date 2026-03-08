package com.pmdm.agenda.data.services

import com.google.gson.annotations.SerializedName

data class ContactoApi(
    val id: Int,
    @SerializedName(value = "nombre")
    val nombre: String,
    val apellidos: String,
    val telefono: String,
    val email: String,
    val urlFoto: String?,
    val categorias: String
)
