package com.pmdm.agenda.data.services

import retrofit2.Response
import retrofit2.http.*

interface ContactoService {
    @GET("contactos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun contactos(): Response<List<ContactoApi>>

    @GET("contactos/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun contacto(@Path("id") id: Int): Response<ContactoApi>

    @GET("contactos/count")
    @Headers("Accept: text/plain", "Content-Type: application/json")
    suspend fun count(): Response<Int>

    @POST("contactos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body c : ContactoApi): Response<RespuestaApi>

    @PUT("contactos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Body c : ContactoApi): Response<RespuestaApi>

    @DELETE("contactos/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id: Int): Response<RespuestaApi>

    @PATCH("contactos/uploadfoto/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun updatefoto(@Path("id") id: Int, @Body  datosImagen: DatosImagenApi): Response<RespuestaApi>
}