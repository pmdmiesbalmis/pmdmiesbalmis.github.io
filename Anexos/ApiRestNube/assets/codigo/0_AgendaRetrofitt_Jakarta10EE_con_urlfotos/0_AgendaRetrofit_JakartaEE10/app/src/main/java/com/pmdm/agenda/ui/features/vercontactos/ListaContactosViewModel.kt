package com.pmdm.agenda.ui.features.vercontactos

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pmdmiesbalmis.components.manejo_errores.InformacionEstadoUiState
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.ui.features.CatergoriaUiState
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.toContactoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaContactosViewModel @Inject constructor(
    private val contactoRepository: ContactoRepository
) : ViewModel() {

    var contatoSleccionadoState: ContactoUiState? by mutableStateOf(null)
        private set

    var contactosState by mutableStateOf(listOf<ContactoUiState>())
        private set

    var filtradoActivoState: Boolean by mutableStateOf(false)
        private set

    var filtroCategoriaState: CatergoriaUiState by mutableStateOf(CatergoriaUiState())
        private set

    var informacionEstadoState: InformacionEstadoUiState by mutableStateOf(InformacionEstadoUiState.Oculta())
        private set

    private fun ContactoUiState.predicadoFiltro(): Boolean {

        var cumpleFiltro = !filtradoActivoState

        if (!cumpleFiltro) // Está en alguna de las categoirias seleccionadas
            cumpleFiltro = categorias.familia && filtroCategoriaState.familia ||
                    categorias.amigos && filtroCategoriaState.amigos ||
                    categorias.trabajo && filtroCategoriaState.trabajo ||
                    categorias.emergencias && filtroCategoriaState.emergencias

        if (!cumpleFiltro) // El contacto no está en ninguna de las categorias seleccionadas
            cumpleFiltro = !categorias.familia && !filtroCategoriaState.familia &&
                    !categorias.amigos && !filtroCategoriaState.amigos &&
                    !categorias.trabajo && !filtroCategoriaState.trabajo &&
                    !categorias.emergencias && !filtroCategoriaState.emergencias

        return cumpleFiltro
    }

    fun onActivarFiltradoClicked() {
        filtradoActivoState = !filtradoActivoState
        cargaContactos()
    }

    fun onFiltroModificado(categorias: CatergoriaUiState) {
        filtroCategoriaState = categorias.copy()
        cargaContactos()
    }

    private fun deseleccionaContacto() {
        contatoSleccionadoState = null
    }

    private suspend fun getContactos(): List<ContactoUiState> {
        val x = contactoRepository.get()
        return x.map { it.toContactoUiState() }
            .filter { c -> c.predicadoFiltro() }
            .toList()
    }


fun cargaContactos() {
    deseleccionaContacto()
    viewModelScope.launch {
        try {

            informacionEstadoState = InformacionEstadoUiState.Informacion(
                mensaje = "Cargando contactos...",
                muestraProgreso = true
            )
            contactosState = getContactos()
            informacionEstadoState = InformacionEstadoUiState.Oculta()
        } catch (e: Exception) {
            Log.d("ListaContactosViewModel", "Cargando Contactos: ${e.localizedMessage}")
            informacionEstadoState = InformacionEstadoUiState.Error(
                mensaje = "Error al cargar los contactos",
                onDismiss = { informacionEstadoState = InformacionEstadoUiState.Oculta() }
            )
            contactosState = listOf()
        }
    }
}

init { cargaContactos() }

fun onItemListaContatoEvent(e: ItemListaContactosEvent) {
    when (e) {
        is ItemListaContactosEvent.OnClickContacto -> {
            contatoSleccionadoState =
                if (contatoSleccionadoState?.id != e.contacto.id) e.contacto else null
        }

        is ItemListaContactosEvent.OnDeleteContacto -> {
            viewModelScope.launch {
                try {
                    informacionEstadoState = InformacionEstadoUiState.Informacion(
                        mensaje = "Borrando a ${contatoSleccionadoState!!.nombre}...",
                        muestraProgreso = true
                    )
                    contactoRepository.delete(contatoSleccionadoState!!.id)
                    contactosState = getContactos()
                    informacionEstadoState = InformacionEstadoUiState.Oculta()
                } catch (e: Exception) {
                    Log.d("ListaContactosViewModel", "Borrando Contacto: ${e.localizedMessage}")
                    informacionEstadoState = InformacionEstadoUiState.Error(
                        mensaje = "Error al borrar el contacto ${contatoSleccionadoState!!.nombre}",
                        onDismiss = { informacionEstadoState = InformacionEstadoUiState.Oculta() }
                    )
                }
            }
        }

        is ItemListaContactosEvent.OnCrearContacto -> {
            e.onNavigateCrearContacto()
        }

        is ItemListaContactosEvent.OnEditContacto -> {
            e.onNavigateEditarContacto(contatoSleccionadoState!!.id)
        }
    }
}
}