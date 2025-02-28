package com.vehiculos.models

data class Coche(
    val id: Int,
    val fabricante: String,
    val modelo: String,
    val año: Int,
    val precio: Float,
    val porcentajeDescuento: Int,
    val descripcion: String,
    val foto: String?,
)
