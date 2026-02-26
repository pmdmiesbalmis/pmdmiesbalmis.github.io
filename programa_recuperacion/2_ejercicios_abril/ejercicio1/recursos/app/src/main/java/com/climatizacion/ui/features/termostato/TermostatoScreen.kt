package com.climatizacion.ui.features.termostato

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.math.MathUtils
import com.climatizacion.ui.composables.SliderConBotones
import com.climatizacion.ui.features.ModoTermostato
import com.climatizacion.ui.features.TermostatoUiState
import com.climatizacion.ui.theme.ClimatizacionTheme
import com.climatizacion.R
import com.github.pmdmiesbalmis.components.ui.icons.Filled

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

        Icon(
            painter = painterResource(id = id),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopCenter),
            tint = color,
            contentDescription = "Modo"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectorModo(
    encendido: Boolean,
    modo: ModoTermostato,
    onSeleccionarModo : (ModoTermostato) -> Unit,
    modifier: Modifier = Modifier
) {
    data class Opcion(val modo: ModoTermostato, val icono: Int)
    val opciones = listOf(
        Opcion( modo = ModoTermostato.Frio, icono = R.drawable.frio),
        Opcion( modo = ModoTermostato.Calefaccion, icono = R.drawable.calefaccion)
    )

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
    ) {
        val color = MaterialTheme.colorScheme.primary
        opciones.forEachIndexed { index, opcion ->
            SegmentedButton(
                enabled = encendido,
                shape = SegmentedButtonDefaults.itemShape(index = index, count = opciones.size),
                selected = modo == opcion.modo,
                onClick = { onSeleccionarModo(opcion.modo) },
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        painter = painterResource(id = opcion.icono),
                        contentDescription = opcion.modo.name,
                        tint = color
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = opcion.modo.name,
                        color = color
                    )
                }
            }
        }
    }
}

@Composable
private fun InterruptorEncendido(
    encendido: Boolean,
    onCambioInterruptor: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Badge(
            modifier = Modifier.defaultMinSize(minWidth = 28.dp),
            containerColor = if (encendido) Color.LightGray
            else Color(0xFFD32F2F),
            content = {
                Text(
                    text = "off",
                    color = Color.White
                )
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = encendido,
            onCheckedChange = onCambioInterruptor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Badge(
            modifier = Modifier.defaultMinSize(minWidth = 28.dp),
            containerColor = if (encendido) Color(0xFF388E3C)
            else Color.LightGray,
            content = {
                Text(
                    text = "on",
                    color = Color.White
                )
            }
        )
    }
}

@Composable
private fun SelectorTemperatura(
    encendido: Boolean,
    temperaturaEstancia: Int,
    temperaturaTermostato: Int,
    modo: ModoTermostato,
    rango: IntRange,
    onCambiarTemperatura: (Int) -> Unit,
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
    val colorProgreso by remember(encendido, tantoPorUnoSegunRango) {
        derivedStateOf {
            if (encendido) colorSegunRango(tantoPorUnoSegunRango)
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
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { tantoPorUnoSegunRango },
                modifier = modifier.then(Modifier.size(250.dp)),
                strokeWidth = Dp(20f),
                color = colorProgreso,
                trackColor = Color.LightGray
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$temperaturaTermostato°C",
                    color = colorTexto,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium
                )
                IndicardorModo(
                    encendido = encendido,
                    modo = modo,
                    temperaturaTermostato = temperaturaTermostato,
                    temperaturaEstancia = temperaturaEstancia,
                )
            }
        }
        Text(
            text = "${rango.start}°C a ${rango.endInclusive}°C",
            textAlign = TextAlign.Center,
            color = colorTexto
        )
        SliderConBotones(
            sliderPosition = temperaturaTermostato.toFloat(),
            onValueChange = { t -> onCambiarTemperatura(t.toInt()) },
            onValueChangeFinished = {},
            range = rango.start.toFloat()..rango.endInclusive.toFloat(),
            modifier = Modifier.padding(24.dp),
            enabled = encendido
        )
    }
}

@Composable
private fun DatoEstancia(
    valor: Int,
    unidades: String,
    etiqueta: String,
    painter: Painter? = null,
) {
    Row {
        val color = MaterialTheme.colorScheme.primary
        if (painter != null) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painter,
                contentDescription = etiqueta,
                tint = color
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$valor $unidades",
            style = MaterialTheme.typography.headlineMedium,
            color = color
        )
    }
}

@Composable
private fun DatosEstancia(
    humedad: Int,
    temperatura: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DatoEstancia(
            valor = humedad,
            unidades = "%",
            etiqueta = "Humedad",
            painter = painterResource(id = R.drawable.humedad)
        )
        DatoEstancia(
            valor = temperatura,
            unidades = "°C",
            etiqueta = "Temperatura",
            painter = painterResource(id = R.drawable.temperatura)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarTermostato(
    estancia: String,
    onNavigateTrasEditarTermostato: () -> Unit
) = CenterAlignedTopAppBar(
    title = {
        Text(
            text = "Termostato $estancia",
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    navigationIcon = {
        IconButton(onClick = onNavigateTrasEditarTermostato) {
            Icon(
                painter = Filled.getArrowBackIosIcon(),
                contentDescription = "Volver"
            )
        }
    }
)

@Composable
fun ContenidoPrincipal(
    termostatoUiState: TermostatoUiState,
    onCambioInterruptor: (Boolean) -> Unit,
    onSeleccionarModo : (ModoTermostato) -> Unit,
    onCambiarTemperatura: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        DatosEstancia(
            humedad = termostatoUiState.humedadEstancia,
            temperatura = termostatoUiState.temperaturaEstancia,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        InterruptorEncendido(
            encendido = termostatoUiState.encendido,
            onCambioInterruptor = onCambioInterruptor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        SelectorModo(
            encendido = termostatoUiState.encendido,
            modo = termostatoUiState.modo,
            onSeleccionarModo = onSeleccionarModo,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        SelectorTemperatura(
            encendido = termostatoUiState.encendido,
            temperaturaEstancia = termostatoUiState.temperaturaEstancia,
            temperaturaTermostato = termostatoUiState.temperaturaTermostato,
            modo = termostatoUiState.modo,
            rango = TermostatoUiState.RANGO_TEMPERATURAS,
            onCambiarTemperatura = onCambiarTemperatura,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TermostatoScreen(
    termostatoUiState: TermostatoUiState,
    onTermostatoEvent: (TermostatoEvent) -> Unit,
    onNavigateTrasEditarTermostato: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarTermostato(
                termostatoUiState.estancia,
                onNavigateTrasEditarTermostato = onNavigateTrasEditarTermostato
            )
        },
        content = { innerPadding ->
            ContenidoPrincipal(
                termostatoUiState = termostatoUiState,
                onCambioInterruptor = { e -> onTermostatoEvent(TermostatoEvent.onCambioInterruptor(e)) },
                onSeleccionarModo = { m -> onTermostatoEvent(TermostatoEvent.onCambiarModo(m)) },
                onCambiarTemperatura = { t -> onTermostatoEvent(TermostatoEvent.onCambiarTemperatura(t)) },
                modifier = Modifier.padding(innerPadding)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onTermostatoEvent(TermostatoEvent.onGuardarCambios(onNavigateTrasEditarTermostato)) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    painter = Filled.getSaveIcon(),
                    contentDescription = "Configurar"
                )
            }
        }
    )
}

@Preview
@Composable
fun TermostatoScreenPreview() {
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

    val onTermostatoEvent : (TermostatoEvent) -> Unit = { e ->
        when (e) {
            is TermostatoEvent.onCambioInterruptor -> {
                termostatoUiState = termostatoUiState.copy(encendido = e.encendido)
            }
            is TermostatoEvent.onCambiarTemperatura -> {
                termostatoUiState = termostatoUiState.copy(temperaturaTermostato = e.temperatura)
            }
            is TermostatoEvent.onCambiarModo -> {
                termostatoUiState = termostatoUiState.copy(modo = e.modo)
            }
            is TermostatoEvent.onGuardarCambios -> TODO()
        }
    }

    ClimatizacionTheme {
        Surface {
            TermostatoScreen(
                termostatoUiState = termostatoUiState,
                onTermostatoEvent = onTermostatoEvent,
                onNavigateTrasEditarTermostato = { }
            )
        }
    }
}
