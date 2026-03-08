package com.pmdm.agenda.data.room

import androidx.room.*

@Dao
interface ContactoDao {
    @Insert
    suspend fun insert(contacto : ContactoEntity)

    @Delete
    suspend fun delete(contacto : ContactoEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(contacto : ContactoEntity)

    @Query("SELECT COUNT(*) FROM contactos")
    suspend fun count(): Int

    @Query("SELECT * FROM contactos")
    suspend fun get(): List<ContactoEntity>

    @Query("SELECT * FROM contactos WHERE id = :id")
    suspend fun get(id: Int): ContactoEntity
}