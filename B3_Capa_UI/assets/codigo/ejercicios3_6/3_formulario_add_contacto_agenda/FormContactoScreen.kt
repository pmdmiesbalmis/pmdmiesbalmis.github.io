import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.agenda.R
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldPhone
import com.github.pmdmiesbalmis.components.ui.composables.OutlinedTextFieldWithErrorState
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.components.ImagenContacto
import com.pmdm.agenda.ui.features.formcontacto.seleccioncategorias.SeleccionCategoriasConCheckBox
import com.pmdm.agenda.ui.theme.AgendaTheme

@Composable
private fun CabeceraFoto(
    backgroundModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    foto: ImageBitmap?,
    onFotoCambiada: (ImageBitmap) -> Unit
) {

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
            foto = foto,
            anchoBorde = 4.dp
        )
        OutlinedIconButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            onClick = { },
        ) {
            Icon(
                painter = Filled.getPhotoCameraIcon(),
                contentDescription = "Hacer Foto",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        }
        OutlinedIconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { }
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
    verSnackBarState: Boolean = false,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
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
        Pie(
            validacionContactoState = validacionContactoState,
            verSnackBarState = verSnackBarState,
            onContactoEvent = onContactoEvent,
            onNavigateTrasFormContacto = onNavigateTrasFormContacto
        )
    }
}

@Composable
private fun BoxScope.Pie(
    validacionContactoState: ValidacionContactoUiState,
    verSnackBarState: Boolean = false,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    if (verSnackBarState) {
        Snackbar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            dismissAction = {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { onContactoEvent(ContactoEvent.OnDismissError) },
                ) {
                    Icon(
                        painter = Filled.getCancelIcon(),
                        contentDescription = "Cancelar",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                    )
                }
            }
        ) {
            Text(text = validacionContactoState.mensajeError!!)
        }
    } else {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            text = { Text(text = "Guardar") },
            icon = {
                Icon(
                    painter = Filled.getSaveIcon(),
                    contentDescription = "Localized description",
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
                )
            },
            onClick = { onContactoEvent(ContactoEvent.OnSaveContacto(onNavigateTrasFormContacto)) }
        )
    }
}

@Composable
fun FormContactoScreenHorizontal(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    verSnackBarState: Boolean = false,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CabeceraFoto(
            backgroundModifier = Modifier
                .fillMaxHeight()
                .size(height = 0.dp, width = 225.dp),
            imageModifier = Modifier.fillMaxWidth(),
            foto = contactoState.foto,
            onFotoCambiada = { onContactoEvent(ContactoEvent.OnChangeFoto(it)) }
        )
        CuerpoFormulario(
            contactoState = contactoState,
            validacionContactoState = validacionContactoState,
            verSnackBarState = verSnackBarState,
            onContactoEvent = onContactoEvent,
            onNavigateTrasFormContacto = onNavigateTrasFormContacto,
        )
    }
}

@Composable
fun FormContactoScreenVertical(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    verSnackBarState: Boolean = false,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    Column {
        CabeceraFoto(
            backgroundModifier = Modifier
                .fillMaxWidth()
                .size(height = 225.dp, width = 0.dp),
            imageModifier = Modifier.fillMaxHeight(),
            foto = contactoState.foto,
            onFotoCambiada = { onContactoEvent(ContactoEvent.OnChangeFoto(it)) }
        )
        CuerpoFormulario(
            modifier = Modifier.fillMaxHeight(),
            contactoState = contactoState,
            validacionContactoState = validacionContactoState,
            verSnackBarState = verSnackBarState,
            onContactoEvent = onContactoEvent,
            onNavigateTrasFormContacto = onNavigateTrasFormContacto,
        )
    }
}

@Composable
fun FormContactoScreen(
    contactoState: ContactoUiState,
    validacionContactoState: ValidacionContactoUiState,
    verSnackBarState: Boolean = false,
    onContactoEvent: (ContactoEvent) -> Unit,
    onNavigateTrasFormContacto: (actualizaContactos: Boolean) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (maxWidth < 600.dp) {
            FormContactoScreenVertical(
                contactoState = contactoState,
                validacionContactoState = validacionContactoState,
                verSnackBarState = verSnackBarState,
                onContactoEvent = onContactoEvent,
                onNavigateTrasFormContacto = onNavigateTrasFormContacto
            )
        } else {
            FormContactoScreenHorizontal(
                contactoState = contactoState,
                validacionContactoState = validacionContactoState,
                verSnackBarState = verSnackBarState,
                onContactoEvent = onContactoEvent,
                onNavigateTrasFormContacto = onNavigateTrasFormContacto
            )
        }
    }
}

@Preview(
    name = "PORTRAIT",
    device = "id:pixel_3",
    showBackground = true
)
@Preview(
    name = "LANDSCAPE",
    device = "spec:parent=pixel_3,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, fontScale = 1.0f
)
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
                verSnackBarState = verSnackBarState,
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
                            contactoState = contactoState.copy(foto = e.foto)
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
