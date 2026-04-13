package com.climatizacion.data.room

import androidx.room.*

@Entity(tableName = "termostatos")
data class TermostatoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "encendido")
    val encendido: Boolean,
    @ColumnInfo(name = "modo")
    val modo: String,
    @ColumnInfo(name = "temperatura_termostato")
    val temperaturaTermostato: Int,
    @ColumnInfo(name = "estancia")
    val estancia: String
)