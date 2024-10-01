package com.pmdm.proyectobase.ui.features.tema32

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme
import kotlin.math.ceil

@Composable
private fun TextoConForma(
    modifier: Modifier = Modifier,
    texto: String = "Hola Mundo",
    color: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        modifier = modifier.then(Modifier.padding(1.dp)),
        color = color,
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            text = texto
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowConPesos(ancho: Dp, alto: Dp) {
    val maxHijosPorFila = 5
    val filas = 2
    val hijos = maxHijosPorFila * filas
    FlowRow(
        modifier = Modifier.size(ancho, alto),
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = maxHijosPorFila
    ) {
        TextoConForma(
            modifier = Modifier.fillMaxWidth(),
            texto = "Pesos iguales evenly\nAncho = ${ancho} ${maxHijosPorFila} items x fila",
            color = Color.LightGray
        )
        for (i in 1..ceil(hijos / 2f).toInt()) {
            TextoConForma(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1 / hijos.toFloat())
                    .align(Alignment.CenterVertically),
                texto = "IES",
                color = MaterialTheme.colorScheme.tertiary
            )
            TextoConForma(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1 / hijos.toFloat()),
                texto = "Balmis",
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowSinPesos(ancho: Dp, alto: Dp) {
    val maxHijosPorFila = 7
    val filas = 2
    val hijos = maxHijosPorFila * filas
    FlowRow(
        modifier = Modifier.size(ancho, alto),
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        maxItemsInEachRow = maxHijosPorFila
    ) {
        TextoConForma(
            modifier = Modifier.fillMaxWidth(),
            texto = "Sin pesos spaced by 5dp\nAncho = ${ancho} ${maxHijosPorFila} items x fila",
            color = Color.LightGray
        )
        for (i in 1..ceil(hijos / 2f).toInt()) {
            TextoConForma(
                texto = "IES",
                color = MaterialTheme.colorScheme.tertiary
            )
            TextoConForma(
                texto = "Balmis",
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
    }
}


@Preview(
    showBackground = true, name = "FlowColumnYRowPreview",
    device = "spec:width=900dp,height=600dp,dpi=480"
)
@Composable
fun FlowColumnYRowPreview() {
    ProyectoBaseTheme {
        Column()
        {
            Row()
            {
                FlowRowConPesos(500.dp, 300.dp)
                FlowRowConPesos(400.dp, 300.dp)
            }
            Row()
            {
                FlowRowSinPesos(500.dp, 300.dp)
                FlowRowSinPesos(400.dp, 300.dp)
            }
        }
    }
}
