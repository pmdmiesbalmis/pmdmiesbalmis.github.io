package com.vehiculos

import android.app.Application
import com.vehiculos.data.mocks.coche.CocheDaoMock
import com.vehiculos.data.mocks.coche.CocheMock
import com.vehiculos.data.room.CocheDao
import com.vehiculos.data.room.CocheEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class VehiculosApplication : Application() {
    @Inject
    lateinit var daoMock: CocheDaoMock
    @Inject
    lateinit var daoEntity: CocheDao

    private fun CocheMock.toCocheEntity() = CocheEntity(
        id = id,
        fabricante = fabricante,
        modelo = modelo,
        año = año,
        precio = precio,
        porcentajeDescuento = porcentajeDescuento,
        descripcion = descripcion,
        foto = foto
    )

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            if (daoEntity.count() == 0)
                daoMock.get().forEach { daoEntity.insert(it.toCocheEntity()) }
        }
    }
}
