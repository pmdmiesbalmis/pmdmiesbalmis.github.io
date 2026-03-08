package com.pmdm.agenda.data.services

import com.google.gson.annotations.SerializedName

data class ContactoApi(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "nombre")
    val nombre: String,
    @SerializedName(value = "apellidos")
    val apellidos: String,
    @SerializedName(value = "telefono")
    val telefono: String,
    @SerializedName(value = "email")
    val email: String,
    @SerializedName(value = "urlFoto")
    val urlFoto: String?,
    @SerializedName(value = "categorias")
    val categorias: String
)
