package com.pmdm.agenda.data

import com.pmdm.agenda.data.mocks.contacto.ContactoMock
import com.pmdm.agenda.data.room.ContactoEntity
import com.pmdm.agenda.data.services.ContactoApi
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
    urlFoto = urlFoto,
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
    urlFoto = urlFoto,
    correo = correo,
    telefono = telefono,
    categorias = EnumSet.noneOf(Contacto.Categorias::class.java).apply {
        categorias.forEach { add(it.toCategorias()) }
    }
)

fun EnumSet<Contacto.Categorias>.toCategoriaEntity() =
    joinToString(separator = ",") { it.name }

fun Contacto.toContactoEntity() = ContactoEntity(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    urlFoto = urlFoto,
    email = correo,
    telefono = telefono,
    categorias = categorias.toCategoriaEntity()
)

fun String.toEnumSetCategorias(): EnumSet<Contacto.Categorias> {
    val categorias = EnumSet.noneOf(Contacto.Categorias::class.java)
    val textos = this.split(",")
    textos.forEach { categoria ->
        if (!categoria.isNullOrEmpty()) {
            val x="${categoria[0].uppercase()}${categoria.substring(1, categoria.length)}"
            categorias.add(Contacto.Categorias.valueOf(x))
        }
    }
    return categorias
}

fun ContactoEntity.toContacto() = Contacto(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    urlFoto = urlFoto,
    correo = email,
    telefono = telefono,
    categorias = categorias.toEnumSetCategorias()
)

fun Contacto.toContactoApi() = ContactoApi(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    urlFoto = urlFoto,
    email = correo,
    telefono = telefono,
    categorias = categorias.toCategoriaEntity()
)

fun ContactoApi.toContacto() = Contacto(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    urlFoto = urlFoto,
    correo = email,
    telefono = telefono,
    categorias = categorias.toEnumSetCategorias()
)