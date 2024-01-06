package com.pmdm.navegacion.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.navegacion.ui.theme.EjemploNavegacionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    comportamientoAnteScroll: TopAppBarScrollBehavior,
    onNavegarAtras: () -> Unit
) = MediumTopAppBar(
    title = {
        Text("Ejemplo Navegacion Basico", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = onNavegarAtras) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
private fun ContenidoPantalla(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "B",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaBScreen(onNavegarAtras: () -> Unit) {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            TopAppBar(
                comportamientoAnteScroll = comportamientoAnteScroll,
                onNavegarAtras = onNavegarAtras
            )
        },
        content = { innerPadding ->
            ContenidoPantalla(modifier = Modifier.padding(innerPadding))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaBScreenPreview() {
    EjemploNavegacionTheme {
        Surface {
            PantallaBScreen(onNavegarAtras = { })
        }
    }
}