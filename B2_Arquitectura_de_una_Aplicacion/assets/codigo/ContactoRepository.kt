package com.pmdm.agenda.data

import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.models.Contacto

class ContactoRepository {
    private var dao = ContactoDaoMock()

    fun get(): MutableList<Contacto> = dao.get().map { it.toContacto() }.toMutableList()
    fun get(id: Int): Contacto? = dao.get(id)?.toContacto()
    fun insert(contacto: Contacto) = dao.get().add(contacto.toContactoMock())
}