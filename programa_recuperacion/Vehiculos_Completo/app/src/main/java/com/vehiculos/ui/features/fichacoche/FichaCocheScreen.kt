package com.vehiculos.ui.features.fichacoche

import android.util.Range
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vehiculos.R
import com.vehiculos.ui.composables.BadgeShape
import com.github.pmdmiesbalmis.components.ui.composables.TextFieldWithErrorState
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.vehiculos.ui.features.CocheUiState
import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorNumeroEntero
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio

private fun sombra() = Shadow(
    color = Color(0xFF80E6F3),
    offset = Offset(6f, 6f),
    blurRadius = 12f
)

private fun idLogo(fabricante: String) =
    when (fabricante) {
        "Volkswagen" -> R.drawable.volkswagen
        "Audi" -> R.drawable.audi
        "BMW" -> R.drawable.bmw
        "Ford" -> R.drawable.ford
        "Mercedes" -> R.drawable.mercedes
        else -> R.drawable.ic_launcher_foreground
    }

@Composable
fun Cabecera(
    fabricante: String,
    modelo: String,
    hayDescuento: Boolean
) {
    Box(modifier = Modifier.wrapContentSize()) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.foto),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .padding(12.dp),
                painter = painterResource(id = idLogo(fabricante)),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                alpha = 0.8f
            )
            Text(
                modifier = Modifier.padding(6.dp),
                text = fabricante,
                style = MaterialTheme.typography.displaySmall.copy(
                    shadow = remember { sombra() }
                ),
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd),
            text = modelo,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.displayLarge,
            color = Color(0x77FFFFFF)
        )
        if (hayDescuento)
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp)
                    .align(Alignment.BottomStart),
                imageVector = ImageVector.vectorResource(id = R.drawable.descuento),
                contentDescription = "Descuento",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    Color.White
                )
            )
    }
}

@Composable
fun Precio(
    precio: Float,
    porcentajeDescuento: Int,
    onVerDialogoDescuento: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(90.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            val precioConDescuento = precio - precio * porcentajeDescuento / 100

            Text(
                text = "${"%.0f".format(precioConDescuento)} €",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            if (porcentajeDescuento > 0) {
                Text(
                    text = "antes ${"%.0f".format(precio)} €",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Button(onClick = onVerDialogoDescuento) {
            Column {
                Text(
                    text = if (porcentajeDescuento > 0) "Cambiar" else "Poner en",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "OFERTA",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PocentajeDescuento(
    modifier: Modifier = Modifier,
    porcentajeDescuento: Int
) {
    if (porcentajeDescuento == 0)
        throw IllegalArgumentException("No hay descuento")
    Box(
        modifier = modifier.then(
            Modifier
                .clip(BadgeShape())
                .size(90.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .shadow(
                    elevation = 1.dp,
                    shape = BadgeShape(),
                    spotColor = MaterialTheme.colorScheme.primary
                )
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${porcentajeDescuento}%",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Datos(
    descripcion: String,
    año: Int
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .height(100.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = descripcion,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Año: $año",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun FichaCoche(
    modifier: Modifier = Modifier,
    cocheState: CocheUiState,
    onVerDialogoDescuento: () -> Unit
) = Column(modifier = modifier) {
    val contexto = LocalContext.current
    val imageResource = contexto.resources.getIdentifier(
        "@drawable/${cocheState.foto}",
        null, contexto.packageName
    )
    Cabecera(
        fabricante = cocheState.fabricante,
        modelo = cocheState.modelo,
        hayDescuento = cocheState.porcentajeDescuento > 0
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.large
                ),
            model = imageResource,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        if (cocheState.porcentajeDescuento > 0)
            PocentajeDescuento(
                modifier = Modifier.align(Alignment.BottomEnd),
                porcentajeDescuento = cocheState.porcentajeDescuento
            )
    }
    Datos(
        descripcion = cocheState.descripcion,
        año = cocheState.año
    )
    Precio(
        precio = cocheState.precio,
        porcentajeDescuento = cocheState.porcentajeDescuento,
        onVerDialogoDescuento = onVerDialogoDescuento
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarFichaCoche(
    comportamientoAnteScroll: TopAppBarScrollBehavior
    = TopAppBarDefaults.pinnedScrollBehavior(),
    onNavigateTrasVerFicha: () -> Unit
) = CenterAlignedTopAppBar(
    title = {
        Text(
            text = "Ficha Vehículo",
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    navigationIcon = {
        IconButton(onClick = onNavigateTrasVerFicha) {
            Icon(
                painter = Filled.getArrowBackIosIcon(),
                contentDescription = "Volver a galería de vehículos",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FichaCocheScreen(
    cocheState: CocheUiState,
    verDialogoDescuentoState: Boolean,
    onVerDialogoDescuento: () -> Unit,
    onCancelarDialogoDescuento: () -> Unit,
    onAceptarDialogoDescuento: (porcentajeDescuento: Int) -> Unit,
    onNavigateTrasVerFicha: () -> Unit
) {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            TopAppBarFichaCoche(
                comportamientoAnteScroll = comportamientoAnteScroll,
                onNavigateTrasVerFicha = onNavigateTrasVerFicha
            )
        },
        content = { innerPadding ->
            FichaCoche(
                modifier = Modifier.padding(innerPadding),
                cocheState = cocheState,
                onVerDialogoDescuento = onVerDialogoDescuento
            )

            if (verDialogoDescuentoState) {
                val validadorPorcentaje = remember {
                    ValidadorCompuesto<String>()
                        .add(ValidadorTextoNoVacio("El mínimo descuento es 0%"))
                        .add(
                            ValidadorNumeroEntero(
                                Range(0, 99),
                                "El descuento debe estar entre 0% y 99%"
                            )
                        )
                }
                var porcentajeDescuentoState by remember {
                    mutableStateOf(cocheState.porcentajeDescuento)
                }
                var validacionPorcentaje by remember {
                    mutableStateOf(
                        validadorPorcentaje.valida(porcentajeDescuentoState.toString())
                    )
                }

                DialogoDescuento(
                    porcentajeDescuento = porcentajeDescuentoState,
                    validacionPorcentaje = validacionPorcentaje,
                    onPorcentajeDescuentoChange = {
                        validacionPorcentaje = validadorPorcentaje.valida(it)
                        if (!validacionPorcentaje.hayError) {
                            porcentajeDescuentoState = it.toInt()
                        }
                        if (it.isEmpty()) {
                            porcentajeDescuentoState = 0
                        }
                    },
                    onAceptarCambios = {  onAceptarDialogoDescuento(porcentajeDescuentoState) },
                    onCancelarCambios = onCancelarDialogoDescuento
                )
            }
        })
}

@Composable
fun DialogoDescuento(
    porcentajeDescuento: Int,
    validacionPorcentaje: Validacion,
    onPorcentajeDescuentoChange: (String) -> Unit,
    onAceptarCambios: () -> Unit,
    onCancelarCambios: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Porcentaje descuento",
                textAlign = TextAlign.Center
            )
        },
        text = {
            TextFieldWithErrorState(
                label = "%",
                textoState = porcentajeDescuento.toString(),
                textoPista = "Porcentaje de descuento",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                validacionState = validacionPorcentaje,
                onValueChange = onPorcentajeDescuentoChange
            )
        },
        confirmButton = {
            TextButton(onClick = { onAceptarCambios() }) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelarCambios) {
                Text(text = "Cancelar")
            }
        },
        onDismissRequest = onCancelarCambios
    )
}

