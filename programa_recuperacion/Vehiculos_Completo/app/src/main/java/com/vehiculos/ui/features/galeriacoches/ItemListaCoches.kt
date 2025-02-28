package com.vehiculos.ui.features.galeriacoches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vehiculos.ui.composables.BadgeShape
import com.vehiculos.ui.features.CocheUiState


@Composable
fun Precio(
    precio: Float,
    porcentajeDescuento: Int
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            val precioConDescuento = precio - precio * porcentajeDescuento / 100

            Text(
                text = "${"%.0f".format(precioConDescuento)} €",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            if (porcentajeDescuento > 0) {
                Text(
                    text = "antes ${"%.0f".format(precio)} €",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        if (porcentajeDescuento > 0)
            PocentajeDescuento(porcentajeDescuento = porcentajeDescuento)
    }
}

@Composable
fun PocentajeDescuento(
    porcentajeDescuento: Int
) {
    if (porcentajeDescuento == 0)
        throw IllegalArgumentException("No hay descuento")

    Box(
        modifier =
            Modifier.clip(BadgeShape())
                .size(60.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .shadow(
                    elevation = 1.dp,
                    shape = BadgeShape(),
                    spotColor = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${porcentajeDescuento}%",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Descripcion(
    fabricante: String,
    modelo: String
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .defaultMinSize(minWidth = 100.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = fabricante,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = modelo,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ItemListaCoches(
    modifier: Modifier = Modifier,
    fichaCocheState: CocheUiState,
    seleccionadoState: Boolean,
    onSeleccionarCoche: (Int) -> Unit
) {
    val backgroundColor = if (seleccionadoState)
        Color(0xFFB3E7FF)
    else
        MaterialTheme.colorScheme.surface
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable { onSeleccionarCoche(fichaCocheState.id) }
                .clip(MaterialTheme.shapes.large)
                .padding(8.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val contexto = LocalContext.current
        val imageResource = contexto.resources.getIdentifier(
            "@drawable/${fichaCocheState.foto}",
            null, contexto.packageName
        )
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(95.dp, 60.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = MaterialTheme.shapes.small
                ),
            model = imageResource,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Descripcion(
            fabricante = fichaCocheState.fabricante,
            modelo = fichaCocheState.modelo
        )
        Precio(
            precio = fichaCocheState.precio,
            porcentajeDescuento = fichaCocheState.porcentajeDescuento
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListaCochesPreview() {
    Surface {
        ItemListaCoches(
            modifier = Modifier.height(90.dp),
            fichaCocheState = CocheUiState(
                id = 1,
                fabricante = "Volkswagen",
                modelo = "Passat",
                año = 2016,
                precio = 43210f,
                porcentajeDescuento = 20,
                descripcion = "Volkswagen Passat 2.0 TDI 150CV BMT Advance DSG 4p",
                foto = "foto_1"
            ),
            seleccionadoState = false,
            onSeleccionarCoche = {}
        )
    }
}