package com.climatizacion.data

import com.climatizacion.data.mocks.termostato.TermostatoMock
import com.climatizacion.models.Termostato

fun TermostatoMock.toTermostato(): Termostato = Termostato(
    id = id,
    encendido = encendido,
    modo = modo,
    temperaturaTermostato = temperaturaTermostato,
    estancia = estancia
)

fun Termostato.toTermostatoMock(): TermostatoMock = TermostatoMock(
    id = id,
    encendido = encendido,
    modo = modo,
    temperaturaTermostato = temperaturaTermostato,
    estancia = estancia
)
