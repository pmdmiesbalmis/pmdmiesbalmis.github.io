package com.pmdm.agenda.data.services

import android.util.Log
import javax.inject.Inject

class ContactoServiceImplementation @Inject constructor(
    private val contactoService: ContactoService
) {
    private val logTag: String = "OkHttp"

    suspend fun count(): Int {
        val mensajeError = "No se ha obtenido resultado"
        try {
            val response = contactoService.count()
            Log.d("REPO", "Elementos recibidos de la API: ${response.toString()}")
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }
    suspend fun get(): List<ContactoApi> {
        val mensajeError = "No se han podido obtener los contactos"
        try {
            val response = contactoService.contactos()
            Log.d("REPO", "Elementos recibidos de la API: ${response.toString()}")
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun get(id: Int): ContactoApi {
        val mensajeError = "No se han podido obtener el contacto con id = $id"
        try {
            val response = contactoService.contacto(id)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun insert(contacto: ContactoApi) {
        val mensajeError = "No se ha podido añadir el contacto"
        try {
            val response = contactoService.insert(contacto)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun update(contacto: ContactoApi) {
        val mensajeError = "No se ha podido actualizar el contacto"
        try {
            val response = contactoService.update(contacto)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun delete(id: Int) {
        val mensajeError = "No se ha podido borrar el contacto"
        try {
            val response = contactoService.delete(id)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun updatefoto(id: Int, base64foto: String) : String {
        val mensajeError = "No se ha podido actualizar la foto del contacto"
        try {
            val response = contactoService.updatefoto(
                                id = id,
                                datosImagen = DatosImagenApi(
                                    extension = "png",
                                    base64Image = base64foto
                                )
                            )
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val respuesta = response.body()
                Log.d(logTag, response.body()?.toString() ?: "Foto subida a ${respuesta!!.mensaje}")
                return respuesta!!.mensaje!!
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }
}