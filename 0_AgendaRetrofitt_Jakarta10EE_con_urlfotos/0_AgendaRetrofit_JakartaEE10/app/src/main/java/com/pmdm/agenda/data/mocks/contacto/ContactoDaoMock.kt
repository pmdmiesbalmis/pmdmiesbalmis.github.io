package com.pmdm.agenda.data.mocks.contacto

import java.util.EnumSet
import javax.inject.Inject

class ContactoDaoMock @Inject constructor() {

    private var contactos = mutableListOf(
        ContactoMock(
            id = 1,
            nombre = "Xusa",
            apellidos = "García Benavente",
            urlFoto = "http://picsum.photos/200",
            correo = "xusa@iesdoctorbalmis.com",
            telefono = "654654654",
            categorias = EnumSet.noneOf(ContactoMock.Categorias::class.java)
        ),
        ContactoMock(
            id = 2,
            nombre = "Jose",
            apellidos = "Balmaseda del Álamo",
            urlFoto = "http://picsum.photos/200",
            correo = "pepe@iesdoctorbalmis.com",
            telefono = "876876876",
            categorias = EnumSet.of(ContactoMock.Categorias.Amigos, ContactoMock.Categorias.Trabajo)
        ),
        ContactoMock(
            id = 3,
            nombre = "Juan José",
            apellidos = "Guarinos Huesca",
            urlFoto = "http://picsum.photos/200",
            correo = "juanjo@iesdoctorbalmis.com",
            telefono = "987987987",
            categorias = EnumSet.of(ContactoMock.Categorias.Familia, ContactoMock.Categorias.Trabajo)),
        ContactoMock(
            id = 4,
            nombre = "Vicente",
            apellidos = "Martínez Martínez",
            urlFoto = "http://picsum.photos/200",
            correo = "vicentem@iesdoctorbalmis.com",
            telefono = "654987765",
            categorias = EnumSet.of(ContactoMock.Categorias.Familia)),
        ContactoMock(
            id = 5,
            nombre = "Vicente José",
            apellidos = "Rico Cuba",
            urlFoto = "http://picsum.photos/200",
            correo = "vicenter@iesdoctorbalmis.com",
            telefono = "6543321876",
            categorias = EnumSet.of(ContactoMock.Categorias.Amigos, ContactoMock.Categorias.Emergencias)),
        ContactoMock(
            id = 6,
            nombre = "Luís",
            apellidos = "Estany Llorens",
            urlFoto = "http://picsum.photos/200",
            correo = "luis@iesdoctorbalmis.com",
            telefono = "678432876",
            categorias = EnumSet.of(ContactoMock.Categorias.Emergencias))
    )

    fun get(): MutableList<ContactoMock> = contactos
    fun get(id: Int): ContactoMock? = contactos.find { u -> u.id == id }
    fun insert(contacto: ContactoMock) = contactos.add(contacto)
    fun update(contacto: ContactoMock) {
        val index = contactos.indexOfFirst { u -> u.id == contacto.id }
        if (index != -1) contactos[index] = contacto
    }
    fun delete(id: Int) {
        val index = contactos.indexOfFirst { u -> u.id == id }
        if (index != -1) contactos.removeAt(index)
    }
}
