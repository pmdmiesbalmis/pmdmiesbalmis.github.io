package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppSuperiorConBarraAppInferior(
    comportamientoAnteScroll: TopAppBarScrollBehavior
    = TopAppBarDefaults.pinnedScrollBehavior()
) = TopAppBar(
    title = {
        Text(
            text = "Ejemplo BottomBar",
            color = TopAppBarDefaults.topAppBarColors().titleContentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    navigationIcon = {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null
            )
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
fun ContenidoPrincipalScaffoldConBarraAppInferior(
    itemSeleccionadoState: Int?,
    onSeleccionarItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(count = 25) {
            Surface(
                color = if (itemSeleccionadoState == it) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSeleccionarItem(it) }
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                    )
            ) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = if (itemSeleccionadoState == it) {
                        FontWeight.ExtraBold
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppInferiorSeleccion(
    comportamientoAnteScroll: BottomAppBarScrollBehavior
    = BottomAppBarDefaults.exitAlwaysScrollBehavior()
) {
    val descripcionEIconos = remember {
        listOf(
            "Eliminar Item" to Icons.Filled.Delete,
            "Completar Item" to Icons.Filled.Favorite,
            "Descargar Item" to Icons.Filled.Download,
            "Editar Item" to Icons.Filled.Share,
        )
    }
    BottomAppBar(
        actions = {
            descripcionEIconos.forEach { (descripcion, icono) ->
                IconButton(
                    onClick = { /* do something */ }) {
                    Icon(
                        imageVector = icono,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = descripcion
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                contentColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Edit, "Localized description")
            }
        },
        scrollBehavior = comportamientoAnteScroll
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppInferiorSinSeleccion(
    comportamientoAnteScroll: BottomAppBarScrollBehavior
    = BottomAppBarDefaults.exitAlwaysScrollBehavior()
) {
    val descripcionEIconos = remember {
        listOf(
            "Buscar Items" to Icons.Filled.Search,
            "Filtrar Items" to Icons.Filled.Filter,
            "Ordenar Items" to Icons.Filled.SortByAlpha
        )
    }
    BottomAppBar(
        actions = {
            descripcionEIconos.forEach { (descripcion, icono) ->
                IconButton(
                    onClick = { /* do something */ }) {
                    Icon(
                        imageVector = icono,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = descripcion
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                contentColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
        scrollBehavior = comportamientoAnteScroll
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConScrollConBarraAppInferior() {
    val comportamientoAnteScrollSup = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val comportamientoAnteScrollInf = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    var itemSeleccionadoState: Int? by remember { mutableStateOf(null) }
    val onSeleccionarItem: (Int) -> Unit = {
        itemSeleccionadoState = if (itemSeleccionadoState != it) it else null
    }
    Scaffold(
        modifier = Modifier
            .nestedScroll(comportamientoAnteScrollInf.nestedScrollConnection)
            .nestedScroll(comportamientoAnteScrollSup.nestedScrollConnection),
        topBar = { BarraAppSuperiorConBarraAppInferior(comportamientoAnteScrollSup) },
        bottomBar = {
            if (itemSeleccionadoState == null)
                BarraAppInferiorSinSeleccion(comportamientoAnteScrollInf)
            else
                BarraAppInferiorSeleccion(comportamientoAnteScrollInf)
        },
        content = { innerPadding ->
            ContenidoPrincipalScaffoldConBarraAppInferior(
                itemSeleccionadoState = itemSeleccionadoState,
                onSeleccionarItem = onSeleccionarItem,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaConScrollConBarraAppInferiorPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaConScrollConBarraAppInferior()
        }
    }
}