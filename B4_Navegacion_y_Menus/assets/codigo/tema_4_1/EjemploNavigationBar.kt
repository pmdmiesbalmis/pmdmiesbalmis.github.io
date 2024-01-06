package com.pmdm.scaffold.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Filter2
import androidx.compose.material.icons.filled.Filter3
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pmdm.scaffold.ui.theme.EjemplosScaffoldTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAplicacionNavBar(
    comportamientoAnteScroll: TopAppBarScrollBehavior
) = TopAppBar(
    title = {
        Text("Ejemplo NavigationBar", maxLines = 1, overflow = TextOverflow.Ellipsis)
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

@Composable
fun ContenidoPrincipalNavBar(
    indexScreenState: Int,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (indexScreenState) {
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
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Pantalla ${indexScreenState + 1}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun NavBar(
    indexScreenState: Int,
    onNavigateToScreen: (Int) -> Unit
) {
    val titlesAndIcons = remember {
        listOf(
            "Pantalla 1" to Icons.Filled.Filter1,
            "Pantalla 2" to Icons.Filled.Filter2,
            "Pantalla 3" to Icons.Filled.Filter3
        )
    }

    NavigationBar {
        titlesAndIcons.forEachIndexed { index, (title, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = title) },
                label = { Text(title) },
                selected = indexScreenState == index,
                onClick = { onNavigateToScreen(index) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNavBar() {
    var indexScreenState by remember { mutableStateOf(0) }
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = { BarraAplicacionNavBar(comportamientoAnteScroll) },
        bottomBar = {
            NavBar(
                indexScreenState = indexScreenState,
                onNavigateToScreen = { indexScreenState = it }
            )
        },
        content = { innerPadding ->
            ContenidoPrincipalNavBar(
                indexScreenState = indexScreenState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PantallaNavBarPreview() {
    EjemplosScaffoldTheme {
        Surface {
            PantallaNavBar()
        }
    }
}