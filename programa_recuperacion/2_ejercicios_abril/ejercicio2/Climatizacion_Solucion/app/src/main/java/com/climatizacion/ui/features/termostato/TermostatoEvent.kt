package com.climatizacion.ui.features.termostato

import com.climatizacion.ui.features.ModoTermostato

sealed interface TermostatoEvent {
    data class onCambioInterruptor(val encendido: Boolean) : TermostatoEvent
    data class onCambiarTemperatura(val temperatura: Int) : TermostatoEvent
    data class onCambiarModo(val modo: ModoTermostato) : TermostatoEvent
    data class onGuardarCambios(val onNavigateTrasEditarTermostato: () -> Unit) : TermostatoEvent
}