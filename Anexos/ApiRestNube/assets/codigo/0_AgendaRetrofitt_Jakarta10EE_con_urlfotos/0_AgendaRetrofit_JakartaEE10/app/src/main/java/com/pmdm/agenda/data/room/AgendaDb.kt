package com.pmdm.agenda.data.room

import android.content.Context
import androidx.room.*

@Database(
    entities = [ContactoEntity::class],
    exportSchema = false,
    version = 1
)
abstract class AgendaDb : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao

    companion object {
        @Volatile
        private var db: AgendaDb? = null

        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            AgendaDb::class.java, "agenda.db"
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    }
}
