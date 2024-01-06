package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Filter2
import androidx.compose.material.icons.filled.Filter3
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAplicacionTabs(
    comportamientoAnteScroll: TopAppBarScrollBehavior
) = TopAppBar(
    title = {
        Text("Ejemplo Pestañas", maxLines = 1, overflow = TextOverflow.Ellipsis)
    },
    navigationIcon = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
        }
    },
    actions = {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs() {
    var tabIndexState by remember { mutableStateOf(0) }
    val titlesAndIcons = remember {
        listOf(
            "Todos" to Icons.Filled.Filter1,
            "Pares" to Icons.Filled.Filter2,
            "Impares" to Icons.Filled.Filter3
        )
    }
    PrimaryTabRow(selectedTabIndex = tabIndexState) {
        titlesAndIcons.forEachIndexed { index, (title, icon) ->
            Tab(
                selected = tabIndexState == index,
                onClick = { tabIndexState = index },
                text = { Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                },
                icon = { Icon(icon, contentDescription = null) }
            )
        }
    }
}

@Composable
fun ContenidoTabs(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Tabs()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp)
        ) {
            items(count = 50) {
                ElevatedCard(
                    modifier = Modifier.widthIn(100.dp).padding(4.dp),
                ) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConTabs() {
    val comportamientoAnteScroll = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = { BarraAplicacionTabs(comportamientoAnteScroll) },
        content = { innerPadding ->
            ContenidoTabs(modifier = Modifier.padding(innerPadding))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaConTabsPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaConTabs()
        }
    }
}