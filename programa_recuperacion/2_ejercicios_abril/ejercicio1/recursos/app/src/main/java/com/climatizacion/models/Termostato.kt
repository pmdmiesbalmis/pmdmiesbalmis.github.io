package com.climatizacion.models

data class Termostato(
    val id: Int,
    val encendido: Boolean,
    val modo: String,
    val temperaturaTermostato: Int,
    val estancia: String
)
