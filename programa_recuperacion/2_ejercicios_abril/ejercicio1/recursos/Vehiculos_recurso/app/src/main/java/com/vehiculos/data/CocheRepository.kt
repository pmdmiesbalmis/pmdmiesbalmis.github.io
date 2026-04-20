package com.vehiculos.data

import com.vehiculos.data.mocks.coche.CocheDaoMock
import com.vehiculos.models.Coche
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocheRepository {
    private val dao : CocheDaoMock = CocheDaoMock()

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

    suspend fun insert(coche: Coche) = withContext(Dispatchers.IO) {
        dao.insert(coche.toCocheMock())
    }

    suspend fun delete(coche: Coche) = withContext(Dispatchers.IO) {
        dao.delete(coche.toCocheMock())
    }

    suspend fun update(coche: Coche) = withContext(Dispatchers.IO) {
        dao.update(coche.toCocheMock())
    }

    suspend fun count(): Int = withContext(Dispatchers.IO) {
        dao.count()
    }
}