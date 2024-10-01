package com.pmdm.proyectobase.ui.features.tema32

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun MyRow(
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment : Alignment.Vertical
) {
    Row(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        val disposicion = horizontalArrangement.toString()
        TextoConForma(
            texto = "Disposición\n" + disposicion.substring(
                disposicion.indexOf('#') + 1
            ),
            color = Color.LightGray
        )
        TextoConForma(
            modifier = Modifier.align(Alignment.Bottom),
            texto = "Balmis",
            color = MaterialTheme.colorScheme.inversePrimary
        )
        TextoConForma(
            texto = "Doctor",
            color = MaterialTheme.colorScheme.tertiary
        )
        TextoConForma(texto = "IES")
    }
}

@Composable
fun ColumnsWithRows() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MyRow(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        )
        MyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        MyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        )
    }
}

@Composable
fun RowConPesosIguales() {
    Row(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(1/3f),
            texto = "Pesos\nIguales",
            color = Color.LightGray
        )
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(1/3f),
            texto = "Balmis",
            color = MaterialTheme.colorScheme.inversePrimary
        )
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(1/3f)
                .align(Alignment.Top),
            texto = "IES",
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun RowConPesosDistintos() {
    Row(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(0.40f),
            texto = "Pesos Distintos\n40%",
            color = Color.LightGray
        )
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(0.40f),
            texto = "Balmis\n40%",
            color = MaterialTheme.colorScheme.inversePrimary
        )
        TextoConForma(
            modifier = Modifier.fillMaxWidth().weight(0.20f),
            texto = "IES\n20%",
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview(showBackground = true, name = "ColumnYRowPreview")
@Composable
fun ColumnYRowPreview() {
    ProyectoBaseTheme {
        // ColumnsWithRows()
        // RowConPesosIguales()
        RowConPesosDistintos()
    }
}
