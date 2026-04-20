package com.vehiculos.ui.features.fichacoche

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
class FichaCocheViewModel @Inject constructor(
    private val cocheRepository : CocheRepository
): ViewModel() {

    var cocheState: Coche? by mutableStateOf(null)
        private set
    var onVolverAtras: () -> Unit = {}
    var onNavegarDialogoDescuento: () -> Unit = {}

    fun setCoche(id: Int?) {
        if (id != null && id != cocheState?.id) {
            viewModelScope.launch {
                cocheState = cocheRepository.get(id)
            }
        }
    }

    fun onFichaCocheEvent(e: FichaCochesEvent) {
        when (e) {
            is FichaCochesEvent.OnVolverAGaleria -> {
                onVolverAtras()
            }
            is FichaCochesEvent.OnCambiarOferta -> onNavegarDialogoDescuento()
            is FichaCochesEvent.OnAceptarDialogoDescuento -> {
                   cocheState = cocheState?.copy(porcentajeDescuento = e.porcentajeDescuento)
                viewModelScope.launch {
                    cocheRepository.update(cocheState!!)
                }
                onVolverAtras()
            }
            is FichaCochesEvent.OnCancelarDialogoDescuento -> onVolverAtras()
        }
    }
}