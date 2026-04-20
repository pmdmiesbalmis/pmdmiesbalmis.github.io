package com.climatizacion.ui.features

import com.climatizacion.models.Termostato

enum class ModoTermostato { Frio, Calefaccion }

data class TermostatoUiState(
    val id: Int,
    val encendido: Boolean,
    val modo: ModoTermostato,
    val temperaturaTermostato: Int,
    val estancia: String,
    val temperaturaEstancia: Int,
    val humedadEstancia: Int
) {
    companion object {
        val RANGO_TEMPERATURAS = 15 .. 32
    }
    fun activable() = when (modo) {
        ModoTermostato.Frio -> temperaturaTermostato < temperaturaEstancia
        ModoTermostato.Calefaccion -> temperaturaTermostato > temperaturaEstancia
    }
}

private fun temperatura(estancia:String) = when(estancia){
    "Cocina" -> 17
    "Salon" -> 27
    "Dormitorio 1" -> 18
    "Dormitorio 2" -> 23
    "Baño" -> 24
    "Despacho" -> 21
    "Garaje" -> 33
    "Baño 1" -> 13
    "Baño 2" -> 25
    "Comedor" -> 17
    else -> 20
}

private fun humedad(estancia: String) = when(estancia){
    "Cocina" -> 40
    "Salon" -> 45
    "Baño 1" -> 50
    "Baño 2" -> 55
    "Comedor" -> 60
    else -> 70
}

fun Termostato.toTermostatoUiState(): TermostatoUiState = TermostatoUiState(
    id = id,
    encendido = encendido,
    modo = when (modo) {
        "F" -> ModoTermostato.Frio
        "C" -> ModoTermostato.Calefaccion
        else -> throw IllegalArgumentException("Modo desconocido")
    },
    temperaturaTermostato = temperaturaTermostato,
    estancia = estancia,
    temperaturaEstancia = temperatura(estancia),
    humedadEstancia = humedad(estancia)
)

fun TermostatoUiState.toTermostato(): Termostato = Termostato(
    id = id,
    encendido = encendido,
    modo = when (modo) {
        ModoTermostato.Frio -> "F"
        ModoTermostato.Calefaccion -> "C"
    },
    temperaturaTermostato = temperaturaTermostato,
    estancia = estancia
)
