package com.pmdm.agenda

import android.app.Application
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.data.room.ContactoDao
import com.pmdm.agenda.data.toContacto
import com.pmdm.agenda.data.toContactoEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class AgendaApplication : Application()  {
    @Inject
    lateinit var daoMock: ContactoDaoMock
    @Inject
    lateinit var daoEntity: ContactoDao

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            if (daoEntity.count() == 0)
                daoMock.get().forEach { daoEntity.insert(it.toContacto().toContactoEntity()) }
        }
    }
}