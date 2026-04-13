package com.climatizacion

import android.app.Application
import com.climatizacion.data.mocks.termostato.TermostatoDaoMock
import com.climatizacion.data.mocks.termostato.TermostatoMock
import com.climatizacion.data.room.TermostatoDao
import com.climatizacion.data.room.TermostatoEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class ClimatizacionApplication : Application() {
    @Inject
    lateinit var daoMock: TermostatoDaoMock
    @Inject
    lateinit var daoEntity: TermostatoDao

    private fun TermostatoMock.toTermostatoEntity() = TermostatoEntity(
        id = id,
        encendido = encendido,
        modo = modo,
        temperaturaTermostato = temperaturaTermostato,
        estancia = estancia
    )

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            if (daoEntity.count() == 0)
                daoMock.get().forEach { daoEntity.insert(it.toTermostatoEntity()) }
        }
    }
}