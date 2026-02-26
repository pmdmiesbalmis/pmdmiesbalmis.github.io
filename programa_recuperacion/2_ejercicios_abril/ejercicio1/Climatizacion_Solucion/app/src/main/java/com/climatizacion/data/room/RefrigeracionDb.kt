package com.climatizacion.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TermostatoEntity::class],
    exportSchema = false,
    version = 1
)
abstract class RefrigeracionDb : RoomDatabase() {
    abstract fun termostatoDao(): TermostatoDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            RefrigeracionDb::class.java, "refrigeracion.db"
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    }
}
