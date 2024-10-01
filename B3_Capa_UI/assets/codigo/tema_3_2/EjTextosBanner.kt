package com.pmdm.proyectobase.ui.features.tema32

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.proyectobase.ui.theme.ProyectoBaseTheme

private fun Modifier.miBordeYPadding(
    color: Color = Color.Transparent,
    shape: Shape
) = border(width = 2.dp, color = color, shape = shape)
    .padding(12.dp)


private fun sombra() = Shadow(
    color = Color.Gray,
    offset = Offset(4f, 4f),
    blurRadius = 4f
)

@Composable
fun Cabecera4(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .miBordeYPadding(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
        )
    ) {
        Text(
            text = "IES Doctor Balmis",
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = remember { sombra() }
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.align(Alignment.End),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
            text = buildAnnotatedString {
                append("Módulo ")
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                ) {
                    append("PMDM")
                }
                append(" 2º DAM")
            })
    }
}

@Preview(showBackground = true, name = "Test4")
@Composable
private fun Test4Preview() {
    ProyectoBaseTheme {
        Column {
            Cabecera4()
        }
    }
}
