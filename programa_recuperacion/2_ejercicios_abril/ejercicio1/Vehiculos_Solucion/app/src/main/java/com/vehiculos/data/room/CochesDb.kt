package com.vehiculos.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CocheEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CochesDb : RoomDatabase() {
    abstract fun cocheDao(): CocheDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            CochesDb::class.java, "coches.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
