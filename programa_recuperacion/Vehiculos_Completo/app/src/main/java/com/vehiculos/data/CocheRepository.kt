package com.vehiculos.data

import com.vehiculos.data.room.CocheDao
import com.vehiculos.models.Coche
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocheRepository @Inject constructor(
    private val dao : CocheDao
) {
    suspend fun get(): List<Coche> = withContext(Dispatchers.IO) {
        dao.get().map { it.toCoche() }
    }

    suspend fun get(id: Int): Coche = withContext(Dispatchers.IO) {
        dao.get(id).toCoche()
    }

    suspend fun getOrdenadosPrecio(): List<Coche> = withContext(Dispatchers.IO) {
        dao.getOrdenadosPrecio().map { it.toCoche() }
    }

    suspend fun getOertas(): List<Coche> = withContext(Dispatchers.IO) {
        dao.getOertas().map { it.toCoche() }
    }

    suspend fun getOertasOrdenadasPrecio(): List<Coche> = withContext(Dispatchers.IO) {
        dao.getOertasOrdenadasPrecio().map { it.toCoche() }
    }

    suspend fun delete(coche: Coche) = withContext(Dispatchers.IO) {
        dao.delete(coche.toCocheEntity())
    }

    suspend fun update(coche: Coche) = withContext(Dispatchers.IO) {
        dao.update(coche.toCocheEntity())
    }
}