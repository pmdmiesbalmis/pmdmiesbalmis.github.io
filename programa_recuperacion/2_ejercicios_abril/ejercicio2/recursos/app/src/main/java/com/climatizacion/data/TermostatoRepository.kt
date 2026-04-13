package com.climatizacion.data

import com.climatizacion.data.mocks.termostato.TermostatoDaoMock
import com.climatizacion.models.Termostato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TermostatoRepository
{
    private val dao : TermostatoDaoMock = TermostatoDaoMock()

    suspend fun get() : List<Termostato> = withContext(Dispatchers.IO) {
        dao.get().map { it.toTermostato() }
    }
    suspend fun get(id: Int) : Termostato  = withContext(Dispatchers.IO) {
        dao.get(id).toTermostato()
    }
    suspend fun update(termostato: Termostato) = withContext(Dispatchers.IO) {
        dao.update(termostato.toTermostatoMock())
    }
    suspend fun delete(termostato: Termostato) = withContext(Dispatchers.IO) {
        dao.delete(termostato.toTermostatoMock())
    }
    suspend fun getEncendidos(): List<Termostato> = withContext(Dispatchers.IO) {
        dao.getEncendidos().map { it.toTermostato() }
    }
    suspend fun getApagados(): List<Termostato> = withContext(Dispatchers.IO) {
        dao.getApagados().map { it.toTermostato() }
    }
}