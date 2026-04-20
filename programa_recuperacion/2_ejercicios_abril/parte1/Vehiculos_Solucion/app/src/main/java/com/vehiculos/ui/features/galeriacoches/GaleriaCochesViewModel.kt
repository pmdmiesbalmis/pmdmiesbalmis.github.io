package com.vehiculos.ui.features.galeriacoches

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vehiculos.data.CocheRepository
import com.vehiculos.models.Coche
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
    var cocheSeleccionadoState : Coche? by mutableStateOf(null)
        private set
    var cochesState: List<Coche> by mutableStateOf(mutableListOf())
        private set

    var onNavegarFichaCoche: (Int) -> Unit = {}

    private suspend fun getCohes() : List<Coche> = if (ordenarPorPrecioState) {
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
    }

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
                    cocheRepository.delete(cocheSeleccionadoState!!)
                    cochesState = getCohes()
                    cocheSeleccionadoState = null
                }
            }
            is GaleriaCochesEvent.OnVerFichaCoche -> {
                onNavegarFichaCoche(cocheSeleccionadoState!!.id)
            }
        }
    }
}