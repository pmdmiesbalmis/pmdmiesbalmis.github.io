package com.climatizacion.ui.features.casa

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Badge
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.math.MathUtils
import com.climatizacion.ui.features.ModoTermostato
import com.climatizacion.ui.features.TermostatoUiState
import com.climatizacion.ui.theme.ClimatizacionTheme
import com.climatizacion.R

private fun tantoPorUnoSegunRango(
    temperatura: Int,
    rango: IntRange
) = (MathUtils.clamp(temperatura, rango.start, rango.endInclusive)
    .toFloat() - rango.start) / (rango.endInclusive - rango.start)

private fun colorSegunRango(
    tantoPorUnoSegunRango: Float
) = when {
    tantoPorUnoSegunRango < 1 / 4f -> Color(0xFF7FE6FF)
    tantoPorUnoSegunRango < 2 / 4f -> Color(0xFF1E88E5)
    tantoPorUnoSegunRango < 3 / 4f -> Color(0xFFBE6A00)
    else -> Color(0xFFB10400)
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun IndicardorModo(
    encendido: Boolean,
    modo: ModoTermostato,
    temperaturaTermostato: Int,
    temperaturaEstancia: Int,
    modifier: Modifier = Modifier
) {
    val id = remember(modo) {
        when (modo) {
            ModoTermostato.Frio -> R.drawable.frio
            ModoTermostato.Calefaccion -> R.drawable.calefaccion
        }
    }

    val activo by remember(encendido, modo, temperaturaTermostato) {
        derivedStateOf {
            encendido && when (modo) {
                ModoTermostato.Frio -> temperaturaTermostato < temperaturaEstancia
                ModoTermostato.Calefaccion -> temperaturaTermostato > temperaturaEstancia
            }
        }
    }

    val color by remember(activo, modo) {
        derivedStateOf {
            if (activo)
                when (modo) {
                    ModoTermostato.Frio -> Color(0xFF1E88E5)
                    ModoTermostato.Calefaccion -> Color(0xFFBE6A00)
                }
            else Color.DarkGray
        }
    }

    Box(
        modifier = modifier
    ) {
        Badge(
            modifier = Modifier
                .defaultMinSize(minWidth = 40.dp)
                .align(Alignment.BottomCenter),
            containerColor = if (encendido) Color(0xFF388E3C)
            else Color(0xFFD32F2F),
            content = {
                Text(
                    text = if (encendido) "on" else "off",
                    color = Color.White
                )
            }
        )
        Icon(
            painter = painterResource(id = id),
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.TopCenter),
            tint = color,
            contentDescription = "Modo"
        )
    }
}

@Composable
private fun IndicadorTemperaturaTermostatoyModo(
    encendido: Boolean,
    temperaturaTermostato: Int,
    modo: ModoTermostato,
    temperaturaEstancia: Int,
    rango: IntRange,
    modifier: Modifier = Modifier
) {
    val tantoPorUnoSegunRango by remember(temperaturaTermostato) {
        derivedStateOf {
            tantoPorUnoSegunRango(
                temperatura = temperaturaTermostato,
                rango = rango
            )
        }
    }

    val colorTexto = MaterialTheme.colorScheme.primary
    val colorProgreso by remember (encendido, tantoPorUnoSegunRango) {
        derivedStateOf {
            if (encendido) colorSegunRango(
                tantoPorUnoSegunRango
            )
            else Color.DarkGray
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { tantoPorUnoSegunRango },
                modifier = modifier.then(Modifier.size(90.dp)),
                strokeWidth = Dp(8f),
                color = colorProgreso,
                trackColor = Color.LightGray
            )
            Text(
                text = "$temperaturaTermostato°C",
                color = colorTexto,
                style = MaterialTheme.typography.headlineMedium
            )
            IndicardorModo(
                encendido = encendido,
                modo = modo,
                temperaturaTermostato = temperaturaTermostato,
                temperaturaEstancia = temperaturaEstancia,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopStart)
            )
        }
        Text(
            text = "${rango.start}°C a ${rango.endInclusive}°C",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            color = colorTexto
        )
    }
}

@Composable
private fun DatosEstancia(
    humedad: Int,
    temperatura: Int,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.humedad),
            contentDescription = "Humedad",
            tint = color
        )
        Text(
            text = "$humedad%",
            color = color
        )

        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.temperatura),
            contentDescription = "Temperatura",
            tint = color
        )
        Text(
            text = "$temperatura°C",
            color = color
        )
    }
}


@Composable
fun TermostatoThumb(
    seleccionado: Boolean,
    termostatoUiState: TermostatoUiState,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = if (seleccionado) CardDefaults.elevatedCardColors().copy(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) else CardDefaults.elevatedCardColors()

    ElevatedCard(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickAction
            )
            .wrapContentHeight()
            .width(140.dp),
        colors = colors,
    ) {
        Text(
            text = termostatoUiState.estancia,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        DatosEstancia(
            humedad = termostatoUiState.humedadEstancia,
            temperatura = termostatoUiState.temperaturaEstancia,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
        IndicadorTemperaturaTermostatoyModo(
            encendido = termostatoUiState.encendido,
            temperaturaTermostato = termostatoUiState.temperaturaTermostato,
            modo = termostatoUiState.modo,
            temperaturaEstancia = termostatoUiState.temperaturaEstancia,
            rango = TermostatoUiState.RANGO_TEMPERATURAS,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview
@Composable
fun TermostatoThumbPreview() {
    var termostatoUiState by remember {
        mutableStateOf(
            TermostatoUiState(
                id = 0,
                encendido = false,
                modo = ModoTermostato.Calefaccion,
                temperaturaTermostato = 24,
                estancia = "Dormitorio 2",
                temperaturaEstancia = 22,
                humedadEstancia = 50
            )
        )
    }

    ClimatizacionTheme {
        Surface {
            TermostatoThumb(
                seleccionado = true,
                termostatoUiState = termostatoUiState,
                onClickAction = { },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
