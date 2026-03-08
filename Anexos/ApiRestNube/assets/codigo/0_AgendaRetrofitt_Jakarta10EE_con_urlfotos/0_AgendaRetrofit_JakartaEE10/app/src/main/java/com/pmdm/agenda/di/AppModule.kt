package com.pmdm.agenda.di

import android.content.Context
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.data.room.AgendaDb
import com.pmdm.agenda.data.room.ContactoDao
import com.pmdm.agenda.data.services.ContactoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val timeout = 200L
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://10.0.2.2:8080/agenda/rest/")
       // .baseUrl("http://agendaapirest-env.eba-6twkstag.us-east-1.elasticbeanstalk.com/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideContactoService(
        retrofit: Retrofit
    ) : ContactoService = retrofit.create(ContactoService::class.java)

    @Provides
    @Singleton
    fun provideAgendaDatabase(
        @ApplicationContext context: Context
    ) : AgendaDb = AgendaDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideContactoDao(
        // La Bd es inyectada por provideAgendaDatabase() y
        // resuelto automáticamente por Hilt
        db: AgendaDb
    ) : ContactoDao = db.contactoDao()

    @Provides
    @Singleton
    fun provideContactoDaoMock() : ContactoDaoMock = ContactoDaoMock()
}