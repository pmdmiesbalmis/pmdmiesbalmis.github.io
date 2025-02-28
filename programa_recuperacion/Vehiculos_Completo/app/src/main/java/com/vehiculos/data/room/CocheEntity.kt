package com.vehiculos.data.room

import androidx.room.*

@Entity(tableName = "coches")
data class CocheEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "fabriucante")
    val fabricante: String,
    @ColumnInfo(name = "modelo")
    val modelo: String,
    @ColumnInfo(name = "año")
    val año: Int,
    @ColumnInfo(name = "precio")
    val precio: Float,
    @ColumnInfo(name = "porcentaje_descuento")
    val porcentajeDescuento: Int,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "foto")
    val foto: String?
)
