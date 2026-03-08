package com.pmdm.agenda.ui.features.formcontacto

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.utilities.device.registroHacerFotoConTakePicture
import com.github.pmdmiesbalmis.utilities.device.registroSelectorDeImagenesConGetContent
import com.github.pmdmiesbalmis.components.manejo_errores.InformacionEstadoUiState
import com.github.pmdmiesbalmis.components.ui.composables.CorrutinaGestionSnackBar
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldPhone
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldWithErrorState
import com.github.pmdmiesbalmis.components.ui.composables.SnackbarCommon
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.agenda.R
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.components.ImagenContacto
import com.pmdm.agenda.ui.features.formcontacto.seleccioncategorias.SeleccionCategoriasConCheckBox
import com.pmdm.agenda.ui.theme.AgendaTheme

@Composable
private fun CabeceraFoto(
    backgroundModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    urlFoto: String?,
    onFotoCambiada: (ImageBitmap) -> Unit
) {
    val slectorDeImagenes = registroSelectorDeImagenesConGetContent(onFotoCambiada)
    val hacerFoto = registroHacerFotoConTakePicture(onFotoCambiada)

    Box(
        modifier = backgroundModifier.then(
            Modifier.background(MaterialTheme.colorScheme.primary)
        ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.bg_dark else R.drawable.bg_light
            ),
            contentDescription = "Fondo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        ImagenContacto(
            modifier = imageModifier.then(Modifier.padding(16.dp)),
            urlFoto = urlFoto,
            anchoBorde = 4.dp
        )
        OutlinedIconButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            onClick = { hacerFoto.launch(android.Manifest.permission.CAMERA) },
        ) {
            Icon(
                painter = Filled.getCancelIcon(),
                contentDescription = "Hacer Foto",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        }
        OutlinedIconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { slectorDeImagenes.launch("image/*") }
        ) {
            Icon(
                painter = Filled.getImageIcon(),
                contentDescription = "Cargar Imagen",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        }
    }
}

@Composable
private fun CuerpoFormulario(
    modifier: Modifier = Modifier,
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    onContactoEvent: (ContactoEvent) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SeleccionCategoriasConCheckBox(
                etiquetaGrupoState = "Categorizar como:",
                categoriaState = contactoState.categorias,
                onCategoriaChanged = { onContactoEvent(ContactoEvent.OnChangeCategoria(it)) }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextFieldWithErrorState(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Nombre",
                    textoState = contactoState.nombre,
                    validacionState = validacionContactoState.validacionNombre,
                    onValueChange = { onContactoEvent(ContactoEvent.OnChangeNombre(it)) }
                )
                OutlinedTextFieldWithErrorState(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Apellidos",
                    textoState = contactoState.apellidos,
                    validacionState = validacionContactoState.validacionApellidos,
                    onValueChange = { onContactoEvent(ContactoEvent.OnChangeApellidos(it)) }
                )
                OutlinedTextFieldEmail(
                    modifier = Modifier.fillMaxWidth(),
                    emailState = contactoState.correo,
                    validacionState = validacionContactoState.validacionCorreo,
                    onValueChange = { onContactoEvent(ContactoEvent.OnChangeCorreo(it)) }
                )
                OutlinedTextFieldPhone(
                    modifier = Modifier.fillMaxWidth(),
                    telefonoState = contactoState.telefono,
                    validacionState = validacionContactoState.validacionTelefono,
                    onValueChange = { onContactoEvent(ContactoEvent.OnChangeTelefono(it)) }
                )
            }
        }

    }
}

@Composable
fun FormContactoScreenHorizontal(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    onContactoEvent: (ContactoEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CabeceraFoto(
            backgroundModifier = Modifier
                .fillMaxHeight()
                .size(height = 0.dp, width = 225.dp),
            imageModifier = Modifier.fillMaxWidth(),
            urlFoto = contactoState.urlFoto,
            onFotoCambiada = { onContactoEvent(ContactoEvent.OnChangeFoto(it)) }
        )
        CuerpoFormulario(
            contactoState = contactoState,
            validacionContactoState = validacionContactoState,
            onContactoEvent = onContactoEvent
        )
    }
}

@Composable
fun FormContactoScreenVertical(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    onContactoEvent: (ContactoEvent) -> Unit
) {
    Column {
        CabeceraFoto(
            backgroundModifier = Modifier
                .fillMaxWidth()
                .size(height = 225.dp, width = 0.dp),
            imageModifier = Modifier.fillMaxHeight(),
            urlFoto = contactoState.urlFoto,
            onFotoCambiada = { onContactoEvent(ContactoEvent.OnChangeFoto(it)) }
        )
        CuerpoFormulario(
            modifier = Modifier.fillMaxHeight(),
            contactoState = contactoState,
            validacionContactoState = validacionContactoState,
            onContactoEvent = onContactoEvent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormContactoTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onNavigateTrasFormContacto(false) }) {
                Icon(
                    painter = Filled.getArrowBackIosIcon(),
                    contentDescription = "Volver a lista de contactos",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        },
    )
}

@Composable
fun FabGuardar(onGuardarContacto: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(text = "Guardar") },
        icon = {
            Icon(
                painter = Filled.getSaveIcon(),
                contentDescription = "Localized description",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
            )
        },
        onClick = onGuardarContacto
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormContactoScreen(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    informacionEstado: InformacionEstadoUiState,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }

    CorrutinaGestionSnackBar(
        snackbarHostState = snackbarHostState,
        informacionEstado = informacionEstado
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FormContactoTopAppBar(
                scrollBehavior = scrollBehavior,
                onNavigateTrasFormContacto = onNavigateTrasFormContacto
            )
        },
        floatingActionButton = {
            FabGuardar(
                onGuardarContacto = {
                    onContactoEvent(
                        ContactoEvent.OnSaveContacto(
                            onNavigateTrasFormContacto
                        )
                    )
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                SnackbarCommon(informacionEstado = informacionEstado)
            }
        }
    )
    {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (maxWidth < 600.dp) {
                FormContactoScreenVertical(
                    contactoState = contactoState,
                    validacionContactoState = validacionContactoState,
                    onContactoEvent = onContactoEvent
                )
            } else {
                FormContactoScreenHorizontal(
                    contactoState = contactoState,
                    validacionContactoState = validacionContactoState,
                    onContactoEvent = onContactoEvent
                )
            }
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
fun FormContactoScreenTest(){
    var contactoState by remember { mutableStateOf(ContactoUiState()) }
    var validacionContactoState by remember { mutableStateOf(ValidacionContactoUiState()) }
    var validadorContacto = remember { ValidadorContacto() }
    var verSnackBarState by remember { mutableStateOf(false) }

    AgendaTheme {
        Surface {
            FormContactoScreen(
                contactoState = contactoState,
                validacionContactoState = validacionContactoState,
                informacionEstado = InformacionEstadoUiState.Oculta(),
                onContactoEvent = { e ->
                    when (e) {
                        is ContactoEvent.OnChangeCategoria -> {
                            contactoState = contactoState.copy(categorias = e.categoria)
                        }

                        is ContactoEvent.OnChangeNombre -> {
                            contactoState = contactoState.copy(nombre = e.nombre)
                            validacionContactoState = validacionContactoState.copy(
                                validacionNombre = validadorContacto.validadorNombre.valida(e.nombre)
                            )
                        }

                        is ContactoEvent.OnChangeApellidos -> {
                            contactoState = contactoState.copy(apellidos = e.apellidos)
                            validacionContactoState = validacionContactoState.copy(
                                validacionApellidos = validadorContacto.validadorApellidos.valida(e.apellidos)
                            )
                        }

                        is ContactoEvent.OnChangeCorreo -> {
                            contactoState = contactoState.copy(correo = e.correo)
                            validacionContactoState = validacionContactoState.copy(
                                validacionCorreo = validadorContacto.validadorCorreo.valida(e.correo)
                            )
                        }

                        is ContactoEvent.OnChangeTelefono -> {
                            contactoState = contactoState.copy(telefono = e.telefono)
                            validacionContactoState = validacionContactoState.copy(
                                validacionTelefono = validadorContacto.validadorTelefono.valida(e.telefono)
                            )
                        }

                        is ContactoEvent.OnChangeFoto -> {
                            contactoState = contactoState.copy(urlFoto = null)
                        }

                        is ContactoEvent.OnDismissError -> {
                            verSnackBarState = false
                        }

                        is ContactoEvent.OnSaveContacto -> {
                            validacionContactoState = validadorContacto.valida(contactoState)
                            if (!validacionContactoState.hayError) {
                                contactoState = ContactoUiState()
                            } else {
                                verSnackBarState = true
                            }
                        }

                    }
                },
                onNavigateTrasFormContacto = {}
            )
        }
    }
}
