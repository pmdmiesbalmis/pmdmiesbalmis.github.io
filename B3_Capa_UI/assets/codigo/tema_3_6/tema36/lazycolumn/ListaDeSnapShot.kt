package com.pmdm.proyectobase.ui.features.tema36.lazycolumn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

@Preview(
    showBackground = true,
    device = "spec:width=700px,height=725px,dpi=480,orientation=portrait"
)
@Composable
private fun DatosListaSnapShot() {
    // Usamos toMutableStateList() para poder modificar la lista y además
    // observar cambios en los objetos. No hace falta usar by
    var datosState = remember { datosAleatorios().toMutableStateList() }
    // Al hacer clic sobre el card tendremos 2 efectos:
    val modificaDato: (Dato) -> Unit = { d ->
        datosState.indexOf(d).takeIf { it >= 0 }?.let { i ->
            // Detecto si estamos en el primer o segundo click viendo si el texto
            // del card empieza por "Modificado "
            // Fíjate que ninuno de los casos de la lógica reasignamos datosState como
            // antes. Sino que modificamos el objeto que está en el índice devuelto.
            if (datosState[i]
                    .texto.subSequence(0, datosState[i].texto.indexOf(" "))
                != "Modificado"
            )
            // El primer click modificará el texto del card por "Modificado id"
                datosState[i] = datosState[i].copy(texto = "Modificado ${d.id}")
            else
            // El segundo click eliminará el card de la lista
                datosState.removeAt(i)
        }
    }
    ProyectoBaseTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DatosEnColumnaDisposicionAnimada(
                datosState = datosState, onClickDato = modificaDato
            )
        }
    }
}
