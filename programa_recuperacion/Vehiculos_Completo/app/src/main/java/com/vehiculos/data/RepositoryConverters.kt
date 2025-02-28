package com.vehiculos.data

import com.vehiculos.data.room.CocheEntity
import com.vehiculos.models.Coche

fun CocheEntity.toCoche(): Coche = Coche(
    id = id,
    fabricante = fabricante,
    modelo = modelo,
    año = año,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    descripcion = descripcion,
    foto = foto
)

fun Coche.toCocheEntity(): CocheEntity = CocheEntity(
    id = id,
    fabricante = fabricante,
    modelo = modelo,
    año = año,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    descripcion = descripcion,
    foto = foto
)

