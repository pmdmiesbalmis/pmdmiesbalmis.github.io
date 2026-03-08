package com.pmdm.agenda.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmdm.agenda.ui.features.formcontacto.ContactoViewModel
import com.pmdm.agenda.ui.features.formcontacto.FormContactoScreen
import com.pmdm.agenda.ui.navigation.AgendaNavHost
import com.pmdm.agenda.ui.theme.AgendaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgendaNavHost()
                }
            }
        }
    }
}

@Composable
fun VerFormContacto(vm : ContactoViewModel = hiltViewModel() ) {
    FormContactoScreen(
        contactoState = vm.contactoState,
        validacionContactoState = vm.validacionContactoState,
        informacionEstado = vm.informacionEstadoState,
        onContactoEvent = vm::onContactoEvent,
        onNavigateTrasFormContacto = {}
    )
}
