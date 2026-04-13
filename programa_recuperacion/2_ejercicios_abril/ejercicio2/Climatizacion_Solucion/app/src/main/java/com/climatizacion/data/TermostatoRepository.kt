package com.climatizacion.data

import com.climatizacion.data.room.TermostatoDao
import com.climatizacion.models.Termostato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TermostatoRepository @Inject constructor(
    private val dao : TermostatoDao
){
    suspend fun get() : List<Termostato> = withContext(Dispatchers.IO) {
        dao.get().map { it.toTermostato() }
    }
    suspend fun get(id: Int) : Termostato  = withContext(Dispatchers.IO) {
        dao.get(id).toTermostato()
    }
    suspend fun update(termostato: Termostato) = withContext(Dispatchers.IO) {
        dao.update(termostato.toTermostatoEntity())
    }
    suspend fun delete(termostato: Termostato) = withContext(Dispatchers.IO) {
        dao.delete(termostato.toTermostatoEntity())
    }
    suspend fun getEncendidos(): List<Termostato> = withContext(Dispatchers.IO) {
        dao.getEncendidos().map { it.toTermostato() }
    }
    suspend fun getApagados(): List<Termostato> = withContext(Dispatchers.IO) {
        dao.getApagados().map { it.toTermostato() }
    }
}