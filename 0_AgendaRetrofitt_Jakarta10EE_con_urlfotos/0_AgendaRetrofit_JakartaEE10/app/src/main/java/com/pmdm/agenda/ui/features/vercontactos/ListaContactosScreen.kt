package com.pmdm.agenda.ui.features.vercontactos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.manejo_errores.InformacionEstadoUiState
import com.github.pmdmiesbalmis.components.ui.composables.CorrutinaGestionSnackBar
import com.github.pmdmiesbalmis.components.ui.composables.SnackbarCommon
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.agenda.R
import com.pmdm.agenda.data.mocks.contacto.ContactoDaoMock
import com.pmdm.agenda.ui.features.CatergoriaUiState
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.formcontacto.seleccioncategorias.SeleccionCategoriasConFilterChip
import com.pmdm.agenda.ui.theme.AgendaTheme
import com.github.pmdmiesbalmis.utilities.imagetools.*
import kotlinx.coroutines.launch

@Composable
fun ListaContactos(
    modifier: Modifier = Modifier,
    contactosState: List<ContactoUiState>,
    contactoSeleccionadoState: ContactoUiState?,
    onContactoClicked: (ContactoUiState) -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Box(modifier = modifier.then(Modifier.fillMaxSize())) {
        LazyColumn(
            contentPadding = PaddingValues(all = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                contactosState,
                key = { it.id }
            ) { contacto ->
                ContactoListItem(
                    modifier = Modifier.animateItem(),
                    contactoUiState = contacto,
                    seleccionadoState = contactoSeleccionadoState?.let { it.id == contacto.id }
                        ?: false,
                    onContactoClicked = onContactoClicked,
                    onEditClicked = onEditClicked,
                    onDeleteClicked = onDeleteClicked
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            onClick = onAddClicked
        ) {
            Icon(
                painter = Filled.getAddIcon(),
                contentDescription = "Crear Contacto")
        }
    }
}

@Composable
fun SwitchActivacionFiltrado(
    modifier: Modifier = Modifier,
    filtradoActivoState: Boolean,
    onActivarFiltradoClicked: (Boolean) -> Unit
) {
    Switch(
        modifier = modifier,
        checked = filtradoActivoState,
        onCheckedChange = onActivarFiltradoClicked,
        thumbContent = {
            Icon(
                painter = if (filtradoActivoState) Filled.getFilterListIcon() else Filled.getFilterListOffIcon(),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaContactosTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onActualizaContactos: () -> Unit,
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = onActualizaContactos) {
                Icon(
                    painter = Filled.getRefreshIcon(),
                    contentDescription = "Actualiza contactos",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaContactosScreen(
    modifier: Modifier = Modifier,
    contactosState: List<ContactoUiState>,
    contactoSeleccionadoState: ContactoUiState?,
    filtradoActivoState: Boolean,
    filtroCategoriaState: CatergoriaUiState,
    informacionEstadoState: InformacionEstadoUiState,
    onActualizaContactos: () -> Unit,
    onActivarFiltradoClicked: (Boolean) -> Unit,
    onFiltroModificado: (CatergoriaUiState) -> Unit,
    onContactoClicked: (ContactoUiState) -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Corrutina que muestra el bottom sheet si no está visible
    LaunchedEffect(
        key1 = !scaffoldState.bottomSheetState.isVisible,
        block = { scaffoldState.bottomSheetState.show() }
    )

    CorrutinaGestionSnackBar(
        snackbarHostState = snackbarHostState,
        informacionEstado = informacionEstadoState
    )

    BottomSheetScaffold(
        sheetContent = {
            SeleccionCategoriasConFilterChip(
                categoriaState = filtroCategoriaState,
                onCategoriaChanged = onFiltroModificado
            )
        },
        sheetDragHandle = {
            SwitchActivacionFiltrado(
                filtradoActivoState = filtradoActivoState,
                onActivarFiltradoClicked = { filtradoOn ->
                    onActivarFiltradoClicked(filtradoOn)
                    scope.launch {
                        if (!filtradoOn) {
                            scaffoldState.bottomSheetState.show()
                        }
                    }
                }
            )
        },
        sheetPeekHeight = 45.dp,
        sheetShape = BottomSheetDefaults.ExpandedShape,
        scaffoldState = scaffoldState,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ListaContactosTopAppBar(
                scrollBehavior = scrollBehavior,
                onActualizaContactos = onActualizaContactos
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                SnackbarCommon(informacionEstado = informacionEstadoState)
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ListaContactos(
                modifier = modifier,
                contactosState = contactosState,
                contactoSeleccionadoState = contactoSeleccionadoState,
                onContactoClicked = onContactoClicked,
                onAddClicked = onAddClicked,
                onEditClicked = onEditClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    }
}

@Preview(
    name = "PORTRAIT",
    device = "spec:width=360dp,height=800dp,dpi=480",
    showBackground = true
)
//@Preview(
//    name = "LANDSCAPE",
//    device = "spec:width=360dp,height=800dp,dpi=480,orientation=landscape",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true, fontScale = 1.0f
//)
@Composable
fun ListaContactosScreenTest() {

    val contactos = ContactoDaoMock().get().map {
        ContactoUiState(
            id = it.id,
            urlFoto =  it.urlFoto,
            nombre = it.nombre,
            apellidos = it.apellidos,
            telefono = it.telefono,
            correo = it.correo,
        )
    }

    var contactoSeleccionadoState: ContactoUiState? by remember { mutableStateOf(null) }
    var filtroCategoriaState: CatergoriaUiState by remember { mutableStateOf(CatergoriaUiState()) }
    var filtradoActivoState: Boolean by remember { mutableStateOf(false) }
    var snackBarCommonUiState: InformacionEstadoUiState by remember {
        mutableStateOf(
            InformacionEstadoUiState.Oculta()
        )
    }

    AgendaTheme {
        Surface {
            ListaContactosScreen(
                contactosState = contactos,
                contactoSeleccionadoState = contactoSeleccionadoState,
                filtradoActivoState = filtradoActivoState,
                filtroCategoriaState = filtroCategoriaState,
                informacionEstadoState = snackBarCommonUiState,
                onActualizaContactos = {},
                onActivarFiltradoClicked = { filtradoActivoState = !filtradoActivoState },
                onFiltroModificado = { c -> filtroCategoriaState = c },
                onContactoClicked = { c ->
                    contactoSeleccionadoState =
                        if (contactoSeleccionadoState == null || c.id != contactoSeleccionadoState!!.id) c.copy() else null
                },
                onAddClicked = {},
                onEditClicked = {},
                onDeleteClicked = {}
            )
        }
    }
}