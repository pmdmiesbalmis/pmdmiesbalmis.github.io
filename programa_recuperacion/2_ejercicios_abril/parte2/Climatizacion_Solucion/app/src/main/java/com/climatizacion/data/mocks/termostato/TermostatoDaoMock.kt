package com.climatizacion.data.mocks.termostato

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TermostatoDaoMock @Inject constructor() {
    private val termostatos = listOf(
        TermostatoMock(
            id = 1,
            encendido = true,
            modo = "F",
            temperaturaTermostato = 20,
            estancia = "Comerdor"
        ),
        TermostatoMock(
            id = 2,
            encendido = false,
            modo = "C",
            temperaturaTermostato = 22,
            estancia = "Cocina"
        ),
        TermostatoMock(
            id = 3,
            encendido = true,
            modo = "F",
            temperaturaTermostato = 18,
            estancia = "Dormitorio 1"
        ),
        TermostatoMock(
            id = 4,
            encendido = false,
            modo = "C",
            temperaturaTermostato = 24,
            estancia = "Baño 1"
        ),
        TermostatoMock(
            id = 5,
            encendido = true,
            modo = "F",
            temperaturaTermostato = 21,
            estancia = "Despacho"
        ),
        TermostatoMock(
            id = 6,
            encendido = false,
            modo = "C",
            temperaturaTermostato = 15,
            estancia = "Garaje"
        ),
        TermostatoMock(
            id = 7,
            encendido = true,
            modo = "F",
            temperaturaTermostato = 12,
            estancia = "Baño 2"
        ),
        TermostatoMock(
            id = 8,
            encendido = false,
            modo = "C",
            temperaturaTermostato = 19,
            estancia = "Dormitorio 2"
        )
    )

    suspend fun get() : List<TermostatoMock> = termostatos
}

