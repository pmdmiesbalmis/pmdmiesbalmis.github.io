package com.climatizacion.data.room

import androidx.room.*

@Dao
interface TermostatoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(contacto : TermostatoEntity)
    @Delete
    suspend fun delete(contacto : TermostatoEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(contacto : TermostatoEntity)
    @Query("SELECT COUNT(*) FROM termostatos")
    suspend fun count(): Int
    @Query("SELECT * FROM termostatos WHERE id = :id")
    suspend fun get(id: Int): TermostatoEntity
    @Query("SELECT * FROM termostatos")
    suspend fun get(): List<TermostatoEntity>
    @Query("SELECT * FROM termostatos WHERE encendido = 1")
    suspend fun getEncendidos(): List<TermostatoEntity>
    @Query("SELECT * FROM termostatos WHERE encendido = 0")
    suspend fun getApagados(): List<TermostatoEntity>
}