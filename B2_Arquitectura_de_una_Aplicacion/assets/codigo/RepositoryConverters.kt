package com.pmdm.agenda.data

import com.pmdm.agenda.data.mocks.contacto.ContactoMock
import com.pmdm.agenda.models.Contacto
import java.util.EnumSet

fun Contacto.Categorias.toCategoriasMock() = when (this) {
    Contacto.Categorias.Amigos -> ContactoMock.Categorias.Amigos
    Contacto.Categorias.Trabajo -> ContactoMock.Categorias.Trabajo
    Contacto.Categorias.Familia -> ContactoMock.Categorias.Familia
    Contacto.Categorias.Emergencias -> ContactoMock.Categorias.Emergencias
}
fun ContactoMock.Categorias.toCategorias() = when (this) {
    ContactoMock.Categorias.Amigos -> Contacto.Categorias.Amigos
    ContactoMock.Categorias.Trabajo -> Contacto.Categorias.Trabajo
    ContactoMock.Categorias.Familia -> Contacto.Categorias.Familia
    ContactoMock.Categorias.Emergencias -> Contacto.Categorias.Emergencias
}

fun Contacto.toContactoMock() = ContactoMock(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    foto = foto,
    correo = correo,
    telefono = telefono,
    categorias = EnumSet.noneOf(ContactoMock.Categorias::class.java).apply {
        categorias.forEach { add(it.toCategoriasMock()) }
    }
)
fun ContactoMock.toContacto() = Contacto(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    foto = foto,
    correo = correo,
    telefono = telefono,
    categorias = EnumSet.noneOf(Contacto.Categorias::class.java).apply {
        categorias.forEach { add(it.toCategorias()) }
    }
)
