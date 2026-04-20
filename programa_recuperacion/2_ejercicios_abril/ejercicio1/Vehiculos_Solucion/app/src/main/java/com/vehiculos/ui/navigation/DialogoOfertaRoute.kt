package com.vehiculos.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.vehiculos.ui.features.dialogodescuento.DescuentoDialog
import com.vehiculos.ui.features.fichacoche.FichaCocheViewModel
import com.vehiculos.ui.features.fichacoche.FichaCochesEvent
import kotlinx.serialization.Serializable

@Serializable
object DialogoDescuentoRoute

fun NavGraphBuilder.dialogoDescuentoDestination(
    vm: FichaCocheViewModel
) {
    dialog<DialogoDescuentoRoute> { backStackEntry ->
        DescuentoDialog(
            porcentajeDescuento = vm.cocheState!!.porcentajeDescuento,
            onAceptarCambios = { porcentaje -> vm.onFichaCocheEvent(
                e = FichaCochesEvent.OnAceptarDialogoDescuento(porcentajeDescuento = porcentaje)
            ) },
            onCancelarCambios = {
                vm.onFichaCocheEvent(e = FichaCochesEvent.OnCancelarDialogoDescuento)
            }
        )
    }
}
