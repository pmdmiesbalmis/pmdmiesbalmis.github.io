package com.climatizacion.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SliderConBotones(
    sliderPosition: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    range: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val steps = remember { range.endInclusive.toInt() - range.start.toInt() + 1 }

    Row(
        modifier = modifier.then(Modifier.fillMaxWidth())
    ) {
        OutlinedIconButton(
            enabled = enabled,
            modifier = Modifier.weight(0.12f),
            onClick = {
                if (sliderPosition > range.start.toInt())
                    onValueChange(sliderPosition - 1f)
            }
        ) {
            Text(
                text = "-",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Slider(
            enabled = enabled,
            modifier = Modifier
                .weight(0.76f)
                .padding(horizontal = 16.dp),
            value = sliderPosition,
            steps = steps,
            valueRange = range,
            onValueChange = onValueChange,
            onValueChangeFinished = onValueChangeFinished
        )

        OutlinedIconButton(
            enabled = enabled,
            modifier = Modifier.weight(0.12f),
            onClick = {
                if (sliderPosition < range.endInclusive.toInt())
                    onValueChange(sliderPosition + 1f)
            }
        ) {
            Text(
                text = "+",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
@Preview (showBackground = true)
fun SliderConBotonesPreview() {
    SliderConBotones(
        sliderPosition = 25f,
        onValueChange = {},
        onValueChangeFinished = {},
        range = 0f..50f,
        modifier = Modifier.padding(16.dp)
    )
}
