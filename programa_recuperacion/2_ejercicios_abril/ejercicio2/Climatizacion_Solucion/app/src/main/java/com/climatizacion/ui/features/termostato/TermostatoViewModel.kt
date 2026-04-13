package com.climatizacion.ui.features.termostato

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.climatizacion.data.TermostatoRepository
import com.climatizacion.ui.features.TermostatoUiState
import com.climatizacion.ui.features.toTermostato
import com.climatizacion.ui.features.toTermostatoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TermostatoViewModel @Inject constructor(
    private val termostatoRepository: TermostatoRepository
) : ViewModel() {
    var termostatoUiState : TermostatoUiState? by mutableStateOf(null)
        private set

    fun setTermostato(id: Int) {
        // TODO:
        //  Para eliminar este runBlocking, es estado no debería ser nullable
        //  y deberíamos asignar un valor por defecto mientras se cargan los
        //  valores del termostato de forma asíncrona.
        runBlocking {
            termostatoUiState = termostatoRepository.get(id).toTermostatoUiState()
        }
    }
    fun onTermostatoEvent(e: TermostatoEvent)  {
        if (termostatoUiState == null)
            throw IllegalStateException("Termostato no inicializado")
        when (e) {
            is TermostatoEvent.onCambioInterruptor -> {
                termostatoUiState = termostatoUiState?.copy(encendido = e.encendido)
            }
            is TermostatoEvent.onCambiarTemperatura -> {
                termostatoUiState = termostatoUiState?.copy(temperaturaTermostato = e.temperatura)
            }
            is TermostatoEvent.onCambiarModo -> {
                termostatoUiState = termostatoUiState?.copy(modo = e.modo)
            }
            is TermostatoEvent.onGuardarCambios -> {
                termostatoUiState?.let {
                    viewModelScope.launch {
                        termostatoRepository.update(it.toTermostato())
                        e.onNavigateTrasEditarTermostato()
                    }
                }
            }
        }
    }
}