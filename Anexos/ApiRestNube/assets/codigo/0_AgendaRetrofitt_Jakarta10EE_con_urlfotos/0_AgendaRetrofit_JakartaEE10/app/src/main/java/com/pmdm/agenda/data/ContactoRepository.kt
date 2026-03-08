package com.pmdm.agenda.data

import com.pmdm.agenda.data.services.ContactoServiceImplementation
import com.pmdm.agenda.models.Contacto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactoRepository @Inject constructor(
    private val contactoService: ContactoServiceImplementation
) {
    suspend fun get(): List<Contacto> = withContext(Dispatchers.IO) {
        contactoService.get().map { it.toContacto() }
    }
    suspend fun get(id: Int): Contacto = withContext(Dispatchers.IO) {
        contactoService.get(id).toContacto()
    }
    suspend fun insert(contacto: Contacto) = withContext(Dispatchers.IO) {
        contactoService.insert(
            contacto.toContactoApi()
        )
    }
    suspend fun update(contacto: Contacto) = withContext(Dispatchers.IO) {
        contactoService.update(contacto.toContactoApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        contactoService.delete(id)
    }
    suspend fun updatefoto(id: Int, base64foto: String) = withContext(Dispatchers.IO) {
        contactoService.updatefoto(id, base64foto)
    }

}