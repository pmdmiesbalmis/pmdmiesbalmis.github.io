package com.vehiculos.di

import android.content.Context
import com.vehiculos.data.CocheRepository
import com.vehiculos.data.mocks.coche.CocheDaoMock
import com.vehiculos.data.room.CocheDao
import com.vehiculos.data.room.CochesDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {
    @Provides
    @Singleton
    fun provideCocheDaoMock(): CocheDaoMock = CocheDaoMock()

    @Provides
    @Singleton
    fun provideCochesDatabase(
        @ApplicationContext context: Context
    ) : CochesDb = CochesDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideCocheDao(
        db: CochesDb
    ) : CocheDao = db.cocheDao()

    @Provides
    @Singleton
    fun provideCocheRepository(
        cocheDao: CocheDao
    ): CocheRepository =
        CocheRepository(cocheDao)
}
