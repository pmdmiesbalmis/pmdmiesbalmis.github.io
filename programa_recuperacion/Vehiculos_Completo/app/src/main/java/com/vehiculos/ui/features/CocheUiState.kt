package com.vehiculos.ui.features

import com.vehiculos.models.Coche

data class CocheUiState(
    val id : Int = 0,
    val fabricante: String = "Desconocido",
    val modelo: String = "Desconocido",
    val año: Int = 1900,
    val precio: Float = 0.0f,
    val porcentajeDescuento: Int = 0,
    val descripcion: String = "Sin descripción",
    val foto: String? = null
)
{
    override fun toString(): String {
        return "$fabricante - $modelo\n$precio€\n$porcentajeDescuento% descuento"
    }
}

fun Coche.toFiChaCocheUiSatate(): CocheUiState = CocheUiState(
    id = id,
    fabricante = fabricante,
    modelo = modelo,
    año = año,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    descripcion = descripcion,
    foto = foto
)

fun CocheUiState.toCoche(): Coche = Coche(
    id = id,
    fabricante = fabricante,
    modelo = modelo,
    año = año,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    descripcion = descripcion,
    foto = foto
)