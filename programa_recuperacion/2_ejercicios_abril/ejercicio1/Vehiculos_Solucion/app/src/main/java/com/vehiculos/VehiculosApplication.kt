package com.vehiculos

import android.app.Application
import com.vehiculos.data.CocheRepository
import com.vehiculos.data.mocks.coche.CocheDaoMock
import com.vehiculos.data.toCoche
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class VehiculosApplication : Application() {
    @Inject
    lateinit var daoMock: CocheDaoMock
    @Inject
    lateinit var coches: CocheRepository

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            if (coches.count() == 0)
                daoMock.get().forEach { coches.insert(coche = it.toCoche()) }
        }
    }
}
