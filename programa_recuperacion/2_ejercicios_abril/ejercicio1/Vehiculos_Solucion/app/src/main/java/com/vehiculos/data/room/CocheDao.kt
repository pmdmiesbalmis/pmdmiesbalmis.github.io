package com.vehiculos.data.room

import androidx.room.*

@Dao
interface CocheDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(contacto : CocheEntity)

    @Delete
    suspend fun delete(contacto : CocheEntity)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(contacto : CocheEntity)

    @Query("SELECT COUNT(*) FROM coches")
    suspend fun count(): Int

    @Query("SELECT * FROM coches")
    suspend fun get(): List<CocheEntity>

    @Query("SELECT * FROM coches ORDER BY precio ASC")
    suspend fun getOrdenadosPrecio(): List<CocheEntity>

    @Query("SELECT * FROM coches WHERE porcentaje_descuento > 0")
    suspend fun getOertas(): List<CocheEntity>

    @Query("SELECT * FROM coches WHERE porcentaje_descuento > 0 ORDER BY precio ASC")
    suspend fun getOertasOrdenadasPrecio(): List<CocheEntity>

    @Query("SELECT * FROM coches WHERE id = :id")
    suspend fun get(id: Int): CocheEntity
}