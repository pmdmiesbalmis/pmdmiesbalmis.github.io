package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Dato(val id: String, val texto: String)

fun generaId(idsGenerados: MutableSet<Int>): Int {
    var id = (0..9999).random()
    while (idsGenerados.contains(id))
        id = (0..9999).random()
    idsGenerados.add(id)
    return id
}

// Genera un id aleatorio
fun datosAleatorios(): List<Dato> {
    val idsGenerados = mutableSetOf<Int>()
    return (0..20).map {
        val id = generaId(idsGenerados)
        Dato(
            id = id.toString(),
            texto = "Dato $id"
        )
    }
}

@OptIn(ExperimentalUuidApi::class)
fun datosAleatorios2(): List<Dato> {
    return (0..20).map {
        val id = Uuid.random().hashCode()
        Dato(
            id = id.toString(),
            texto = "Dato $id"
        )
    }
}


