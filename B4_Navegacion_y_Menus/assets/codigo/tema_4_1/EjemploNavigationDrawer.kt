package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Filter2
import androidx.compose.material.icons.filled.Filter3
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppEnScaffoldDentroNavDrawer(
    onClickActionMenu: () -> Unit,
    comportamientoAnteScroll: TopAppBarScrollBehavior
) = TopAppBar(
    title = {
        Text("Ejemplo Nav Drawer", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = onClickActionMenu) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
        }
    },
    actions = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@Composable
fun PantallaPrincipalScaffoldDentroNavDrawer(
    selecteItemState: ItemMenuEjemploNavDrawer,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (selecteItemState.index) {
        0 -> MaterialTheme.colorScheme.primaryContainer
        1 -> MaterialTheme.colorScheme.secondaryContainer
        else -> MaterialTheme.colorScheme.tertiaryContainer
    }
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
        ),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = selecteItemState.nombre,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

enum class ItemMenuEjemploNavDrawer(
    val index: Int,
    val icono: ImageVector,
    val nombre: String
) {
    Pantalla1(
        index = 0,
        icono = Icons.Filled.Filter1,
        nombre = "Pantalla 1"
    ),
    Pantalla2(
        index = 1,
        icono = Icons.Filled.Filter2,
        nombre = "Pantalla 2"
    ),
    Pantalla3(
        index = 2,
        icono = Icons.Filled.Filter3,
        nombre = "Pantalla 3"
    )
}

@Composable
fun ContenidoNavDrawer(
    selecteItemState: ItemMenuEjemploNavDrawer,
    onItemSelected: (ItemMenuEjemploNavDrawer) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember {
        listOf(ItemMenuEjemploNavDrawer.Pantalla1, ItemMenuEjemploNavDrawer.Pantalla2, ItemMenuEjemploNavDrawer.Pantalla3)
    }
    ModalDrawerSheet(modifier = modifier) {
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.icono, contentDescription = item.nombre) },
                label = { Text(item.nombre) },
                selected = item.index == selecteItemState.index,
                onClick = { onItemSelected(item) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
fun PantallaConNavDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(ItemMenuEjemploNavDrawer.Pantalla1) }
    val scope = rememberCoroutineScope()
    val onItemSelected: (ItemMenuEjemploNavDrawer) -> Unit = {
        scope.launch { drawerState.close() }
        selectedItem = it
    }
    val onClickActionMenu: () -> Unit = {
        scope.launch { drawerState.open() }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ContenidoNavDrawer(
                selecteItemState = selectedItem,
                onItemSelected = onItemSelected
            )
        },
        content = {
            ScaffoldDentroNavDrawer(
                selecteItemState = selectedItem,
                onClickActionMenu = onClickActionMenu,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDentroNavDrawer(
    selecteItemState: ItemMenuEjemploNavDrawer,
    onClickActionMenu: () -> Unit
) {
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            BarraAppEnScaffoldDentroNavDrawer(
                onClickActionMenu = onClickActionMenu,
                comportamientoAnteScroll = comportamientoAnteScroll
            )
        },
        content = { innerPadding ->
            PantallaPrincipalScaffoldDentroNavDrawer(
                selecteItemState = selecteItemState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaConNavDrawerPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaConNavDrawer()
        }
    }
}