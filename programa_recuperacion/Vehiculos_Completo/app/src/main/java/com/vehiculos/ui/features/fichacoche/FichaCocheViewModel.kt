package com.vehiculos.ui.features.fichacoche

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
class FichaCocheViewModel @Inject constructor(
    private val cocheRepository : CocheRepository
): ViewModel() {

    var verDialogoDescuentoState by mutableStateOf(false)
        private set

    var cocheState : CocheUiState by mutableStateOf(CocheUiState())
        private set

    fun setCoche(id: Int?) {
        if (id != null && id != cocheState.id) {
            viewModelScope.launch {
                cocheState = cocheRepository.get(id).toFiChaCocheUiSatate()
            }
        }
    }

    fun onVerDialogoDescuento() {
        verDialogoDescuentoState = true
    }

    fun onCancelarDialogoDescuento() {
        verDialogoDescuentoState = false
    }

    fun onAceptarDialogoDescuento(porcentajeDescuento: Int) {
        cocheState = cocheState.copy(porcentajeDescuento = porcentajeDescuento)
        viewModelScope.launch { cocheRepository.update(cocheState.toCoche()) }
        verDialogoDescuentoState = false
    }
}