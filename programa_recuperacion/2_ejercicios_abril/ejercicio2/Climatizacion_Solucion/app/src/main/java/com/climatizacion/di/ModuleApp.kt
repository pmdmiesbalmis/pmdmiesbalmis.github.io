package com.climatizacion.di


import android.content.Context
import com.climatizacion.data.TermostatoRepository
import com.climatizacion.data.room.RefrigeracionDb
import com.climatizacion.data.room.TermostatoDao
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
    fun provideRefrigeracionDatabase(
        @ApplicationContext context: Context
    ) : RefrigeracionDb = RefrigeracionDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideTermostatoDao(
        db: RefrigeracionDb
    ) : TermostatoDao = db.termostatoDao()

    @Provides
    @Singleton
    fun provideCocheRepository(
        termostatoDao: TermostatoDao
    ): TermostatoRepository =
        TermostatoRepository(termostatoDao)
}
