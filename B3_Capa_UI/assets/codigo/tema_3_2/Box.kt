package com.pmdm.proyectobase.ui.features.tema32

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

@Composable
private fun TextoConForma(
    modifier: Modifier = Modifier,
    texto : String = "Hola Mundo",
    color : Color = MaterialTheme.colorScheme.primary) {
    Surface(
        modifier = modifier.then(Modifier.padding(1.dp)),
        color = color,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center,
            text = texto)
    }
}

@Composable
fun BoxApiladoAlCento() {
    Box (contentAlignment = Alignment.Center){
        TextoConForma(
            modifier = Modifier.size(150.dp, 150.dp),
            texto = "Balmis",
            color = MaterialTheme.colorScheme.inversePrimary)
        TextoConForma(
            texto = "Doctor",
            color = MaterialTheme.colorScheme.tertiary)
        TextoConForma(
            texto = "IES")
    }
}

@Composable
fun BoxConAlineacionesIndependientes() {
    Box {
        TextoConForma(
            modifier = Modifier.align(Alignment.Center)
                               .size(150.dp, 150.dp),
            texto = "Balmis",
            color = MaterialTheme.colorScheme.inversePrimary)
        TextoConForma(
            modifier = Modifier.align(Alignment.CenterStart),
            texto = "Doctor",
            color = MaterialTheme.colorScheme.tertiary)
        TextoConForma(
            modifier = Modifier.align(Alignment.BottomEnd),
            texto = "IES")
    }
}


@Preview(showBackground = true, name = "BoxPreview")
@Composable
fun BoxPreview() {
    ProyectoBaseTheme {
        // BoxApiladoAlCento()
        BoxConAlineacionesIndependientes()
    }
}

