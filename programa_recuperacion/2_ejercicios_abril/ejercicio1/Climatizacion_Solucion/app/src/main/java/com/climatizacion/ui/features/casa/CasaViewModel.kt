package com.climatizacion.ui.features.casa

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
import javax.inject.Inject

@HiltViewModel
class CasaViewModel @Inject constructor(
    private val termostatoRepository: TermostatoRepository
) : ViewModel() {

    var termostatos by mutableStateOf(listOf<TermostatoUiState>())
        private set

    var termostatoSeleccionadoState: TermostatoUiState? by mutableStateOf(null)
        private set
    var filtro by mutableStateOf(FiltroTermostatos.SIN_FILTRO)
        private set

    init {
        viewModelScope.launch { termostatos = getTermostatos() }
    }

    private val deseleccionarTermostato: () -> Unit = { termostatoSeleccionadoState = null }
    fun ActualizaTermostatos() {
        deseleccionarTermostato()
        viewModelScope.launch { termostatos = getTermostatos() }
    }

    private suspend fun getTermostatos() = when (filtro) {
        FiltroTermostatos.ACTIVOS -> termostatoRepository.getEncendidos()
            .map { it.toTermostatoUiState() }
        FiltroTermostatos.ACTIVABLES -> termostatoRepository.getApagados()
            .map { it.toTermostatoUiState() }
            .filter { it.activable() }
        else -> termostatoRepository.get().map { it.toTermostatoUiState() }
    }
    fun onCasaEvent(e: CasaEvent) {
        when (e) {
            is CasaEvent.OnSeleccionarTermostato -> {
                termostatoSeleccionadoState =
                    if (termostatoSeleccionadoState?.id != e.termostato.id) e.termostato else null
            }

            is CasaEvent.onBorrarTermostato -> {
                termostatoSeleccionadoState?.let {
                    viewModelScope.launch {
                        deseleccionarTermostato()
                        termostatoRepository.delete(it.toTermostato())
                        termostatos = getTermostatos()
                    }
                }
            }

            is CasaEvent.onOpcionDeFiltrado -> {
                filtro = e.filtro
                viewModelScope.launch {
                    termostatos = getTermostatos()
                }
            }

            is CasaEvent.onConfigurarTermostato -> {
                e.onNavigateConfigurarTermostato(termostatoSeleccionadoState!!.id)
            }
        }
    }

}