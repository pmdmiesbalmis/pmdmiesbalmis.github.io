package com.pmdm.proyectobase.ui.features.tema32

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme
import com.pmdm.proyectobase.R

@Preview(showBackground = true, name = "ImagenesPreview")
@Composable
fun ImagenesPreview() {
    ProyectoBaseTheme {
        Cabecera5 { Contenido() }
    }
}

private fun sombra() = Shadow(
    color = Color.Gray,
    offset = Offset(4f, 4f),
    blurRadius = 4f
)

@Composable
private fun Contenido() {
    // El cóntenido irá en una fila con el logo 20% y el texto 80%.
    // Además, lo alinearemos verticalmente al centro.
    Row(
        // Debemos intentar ocupar todo el espacio en ancho.
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.weight(0.2f), // Ocupa en ancho el 20% de la fila
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo IES Balmis",
            // Cambiamos el color de la imagen para usar el color primario
            // de nuestro tema de Material.
            colorFilter = ColorFilter.tint(
                MaterialTheme.colorScheme.primary
            ),
            // La imagen se escala para que ocupe todo el espacio recortándose.
            // Esto es, El alto de la imagen de fondo y el ancho del 20% de la fila.
            contentScale = ContentScale.Crop
        )
        Text(
            text = "IES Doctor Balmis",
            modifier = Modifier
                .scale(1.5f)    // Escalamos el texto al 150%
                .weight(0.8f),  // Ocupa en ancho el 80% de la fila
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = remember { sombra() }
            ),
            color = MaterialTheme.colorScheme.primary,
            // Alienamos el texto al centro del 80% que ocupa en la fila.
            textAlign = TextAlign.Center
        )
    }
}

@Composable
// Pasamos un lambda como parámetro con el composable que
// renderizará el contenido de la cabecera
fun Cabecera5(contenido: @Composable () -> Unit = {}) {
    // Definimos una caja donde superponderemos la
    // imagen de fondo y el contenido alineados al centro.
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Ponemos la imagen de fondo de la fachada del Balmis
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
                // Recortamos la iamagen con el shape definido en Tema (Material).
                .clip(MaterialTheme.shapes.medium),
            // Obtenemos Painter del recurso de imagen de la fachada.
            painter = painterResource(id = R.drawable.balmis),
            contentDescription = "Fachada del IES Balmis",
            // La imagen se escala para que ocupe todo el espacio recortándose
            contentScale = ContentScale.Crop
        )
        // Superponemos el contenido de la cabecera a la imagen.
        contenido()
    }
}