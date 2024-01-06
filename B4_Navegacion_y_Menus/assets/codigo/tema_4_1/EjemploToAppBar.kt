package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAplicacion(
    comportamientoAnteScroll: TopAppBarScrollBehavior
) = TopAppBar(
    title = {
        // El texto en TopAppBar solo puede tener una línea
        Text("Título de una línea", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
        }
    },
    actions = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.FirstPage, contentDescription = null)
        }
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
fun ContenidoPrincipalScaffold(
    modifier: Modifier = Modifier
) {
    val colors = remember { listOf(Color(0xFF50A2E4), Color(0xFFFFFFFF)) }
    LazyColumn(modifier = modifier) {
        items(count = 25) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(colors[it % colors.size])
            ) {
                Text(
                    text = "Item $it", modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConScroll() {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = { BarraAplicacion(comportamientoAnteScroll) },
        content = { innerPadding ->
            ContenidoPrincipalScaffold(modifier = Modifier.padding(innerPadding))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaConScrollPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaConScroll()
        }
    }
}