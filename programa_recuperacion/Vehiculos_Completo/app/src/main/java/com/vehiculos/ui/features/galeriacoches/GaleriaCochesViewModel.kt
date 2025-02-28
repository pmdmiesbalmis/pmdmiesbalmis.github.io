package com.vehiculos.ui.features.galeriacoches

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vehiculos.data.CocheRepository
import com.vehiculos.ui.features.CocheUiState
import com.vehiculos.ui.features.toCoche
import com.vehiculos.ui.features.toFiChaCocheUiSatate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GaleriaCochesViewModel @Inject constructor(
    private val cocheRepository : CocheRepository
): ViewModel() {

    var ordenarPorPrecioState by mutableStateOf(false)
        private set
    var filtrarPorDescuentoState by mutableStateOf(false)
        private set
    var cocheSeleccionadoState : CocheUiState? by mutableStateOf(null)
        private set
    var cochesState: List<CocheUiState> by mutableStateOf(mutableListOf())
        private set

    private suspend fun getCohes() : List<CocheUiState> = if (ordenarPorPrecioState) {
        if (filtrarPorDescuentoState) {
            cocheRepository.getOertasOrdenadasPrecio()
        } else {
            cocheRepository.getOrdenadosPrecio()
        }
    } else {
        if (filtrarPorDescuentoState) {
            cocheRepository.getOertas()
        } else {
            cocheRepository.get()
        }
    }.map { it.toFiChaCocheUiSatate() }

    init { onActualizarCoches() }

    fun onActualizarCoches() {
        cocheSeleccionadoState = null
        viewModelScope.launch { cochesState = getCohes() }
    }

    fun onGaleriaCochesEvent(e: GaleriaCochesEvent) {
        when(e) {

            is GaleriaCochesEvent.OnSeleccionarCoche -> {
                cocheSeleccionadoState = if (cocheSeleccionadoState?.id != e.coche.id) e.coche else null
            }
            is GaleriaCochesEvent.OnOrdenarPorPrecio -> {
                ordenarPorPrecioState = !ordenarPorPrecioState
                viewModelScope.launch { cochesState = getCohes() }
            }
            is GaleriaCochesEvent.OnFiltrarPorDescuento -> {
                filtrarPorDescuentoState = !filtrarPorDescuentoState
                viewModelScope.launch { cochesState = getCohes() }
            }
            is GaleriaCochesEvent.OnBorrarVehiculo -> {
                viewModelScope.launch {
                    cocheRepository.delete(cocheSeleccionadoState!!.toCoche())
                    cochesState = getCohes()
                    cocheSeleccionadoState = null
                }
            }
        }
    }
}