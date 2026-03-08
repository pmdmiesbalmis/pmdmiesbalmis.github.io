package com.pmdm.agenda.data.room

import androidx.room.*

@Entity(tableName = "contactos")
data class ContactoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "apellidos")
    val apellidos: String,
    @ColumnInfo(name = "telefono")
    val telefono: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "url_foto")
    val urlFoto: String?,
    @ColumnInfo(name = "categorias")
    val categorias: String
)
