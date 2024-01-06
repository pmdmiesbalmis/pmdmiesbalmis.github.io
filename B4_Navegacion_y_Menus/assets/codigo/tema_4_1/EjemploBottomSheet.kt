package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppSuperiorBottomSheet(
    comportamientoAnteScroll: TopAppBarScrollBehavior
    = TopAppBarDefaults.pinnedScrollBehavior()
) = TopAppBar(
    title = {
        Text(
            text = "Ejemplo BottomSheet",
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
fun ContenidoPrincipalBottomSheetScaffold(
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
                    MaterialTheme.colorScheme.tertiaryContainer
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
fun PantallaConBottomSheetScaffold() {
    val comportamientoAnteScrollSup = TopAppBarDefaults.pinnedScrollBehavior()

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var itemSeleccionadoState: Int? by remember { mutableStateOf(null) }
    val onSeleccionarItem: (Int) -> Unit = {
        itemSeleccionadoState = if (itemSeleccionadoState != it) it else null
        scope.launch {
            if (itemSeleccionadoState == null)
                scaffoldState.bottomSheetState.show()
            else
                scaffoldState.bottomSheetState.expand()
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.nestedScroll(comportamientoAnteScrollSup.nestedScrollConnection),
        topBar = {
            BarraAppSuperiorBottomSheet(comportamientoAnteScrollSup)
        },
        snackbarHost = { SnackbarHost(scaffoldState.snackbarHostState) },
        sheetContent = {
            if (itemSeleccionadoState != null) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "Item $itemSeleccionadoState seleccionado",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
        },
        sheetPeekHeight = 45.dp,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        content = { innerPadding ->
            ContenidoPrincipalBottomSheetScaffold(
                itemSeleccionadoState = itemSeleccionadoState,
                onSeleccionarItem = onSeleccionarItem,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaConBottomSheetScaffoldPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaConBottomSheetScaffold()
        }
    }
}