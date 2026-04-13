package com.climatizacion.data.mocks.termostato

data class TermostatoMock(
    val id: Int,
    val encendido: Boolean,
    val modo: String,
    val temperaturaTermostato: Int,
    val estancia: String
)
