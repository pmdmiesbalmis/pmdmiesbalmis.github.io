package com.pmdm.agenda.ui.features.vercontactos

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.icons.Filled
import com.pmdm.agenda.ui.features.components.ImagenContacto
import com.pmdm.agenda.ui.theme.AgendaTheme

// Muestra los iconos con las categorías del contacto, por eso
// recibe únicamente el estado de las categorías.
@Composable
fun Categorias(
    categoriasState: CatergoriaUiState
) {
    data class IconoCategoriaUi(
        val visible: Boolean,
        val icon: Painter,
    )

    val iconosCategoriaUi = listOf(
        IconoCategoriaUi(
            visible = categoriasState.amigos,
            icon = categoriasState.amigosIcon()
        ),
        IconoCategoriaUi(
            visible = categoriasState.trabajo,
            icon = categoriasState.trabajoIcon()
        ),
        IconoCategoriaUi(
            visible = categoriasState.familia,
            icon = categoriasState.familiaIcon()
        ),
        IconoCategoriaUi(
            visible = categoriasState.emergencias,
            icon = categoriasState.emergenciasIcon()
        )
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        for (iconUi in iconosCategoriaUi) {
            if (iconUi.visible) {
                Icon(
                    painter = iconUi.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
    }
}

// Muestra todos los datos del contacto menos la imagen asociada.
// Por esa razón los recibe cada uno de los datos del contacto
// excepto la imagen.
@Composable
fun DatosContacto(
    modifier: Modifier = Modifier,
    nombre: String,
    apellidos: String,
    correo: String,
    telefono: String,
    categorias: CatergoriaUiState
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${apellidos}, ${nombre}",
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = correo,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.secondary
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = telefono,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Categorias(categorias)
        }
    }
}

@Preview(
    name = "PORTRAIT",
    device = "spec:width=360dp,height=800dp,dpi=480",
    showBackground = true
)
// Muestra OutlinedIconButton con los iconos de las
// acciones posibles sobre ul contacto seleccionado.
@Composable
fun AccionesContacto(
    onLlamarClicked: () -> Unit = {},
    onCorreoClicked: () -> Unit = {},
    onEditClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    data class Accion(
        val icon: Painter,
        val description: String,
        val onClick: () -> Unit
    )

    val phoneIcon = Filled.getPhoneEnabledIcon()
    val correoIcon = Filled.getMailIcon()
    val editIcon = Filled.getEditIcon()
    val deleteIcon = Filled.getDeleteIcon()
    val acciones = remember {
        listOf(
            Accion(
                icon = phoneIcon,
                description = "Llamar",
                onClick = onLlamarClicked
            ),
            Accion(
                icon = correoIcon,
                description = "Correo",
                onClick = onCorreoClicked
            ),
            Accion(
                icon = editIcon,
                description = "Editar",
                onClick = onEditClicked
            ),
            Accion(
                icon = deleteIcon,
                description = "Eliminar",
                onClick = onDeleteClicked
            )
        )
    }

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize { initialValue, targetValue -> },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        for (accion in acciones) {
            OutlinedIconButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = accion.onClick,
            ) {
                Icon(
                    painter = accion.icon,
                    contentDescription = accion.description,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        }
        Spacer(modifier = Modifier.width(70.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
// Muestra la imagen del contacto, los datos del contacto y un perqueño
// icono que tendrá una animación de rotación cuando el contacto esté
// seleccionado.
@Composable
fun ContenidoPrincipalCardContacto(
    contactoUiState: ContactoUiState,
    seleccionadoState: Boolean,
    modifier: Modifier = Modifier
) {
    // Estado de la rotación del icono de la flecha dependiendo
    // de si el item está seleccionado o no.
    val rotacionIconoDropDown by animateFloatAsState(
        targetValue = if (seleccionadoState) 180f else 0f, label = ""
    )

    Row(
        modifier = modifier.then(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Usa FlowRow para que la imagen se superponga a los datos
        // de contacto cuando no haya suficiente espacio para ambos
        FlowRow(
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImagenContacto(
                    foto = contactoUiState.foto,
                    anchoBorde = 4.dp
                )
            }
            DatosContacto(
                modifier = Modifier,
                nombre = contactoUiState.nombre,
                apellidos = contactoUiState.apellidos,
                correo = contactoUiState.correo,
                telefono = contactoUiState.telefono,
                categorias = contactoUiState.categorias
            )
        }
        Icon(
            painter = Filled.getArrowDropDownIcon(),
            contentDescription = "Seleccionado",
            // El valor viene establecido por un estado que se animará
            // cuando el item esté seleccionado o no.
            modifier = modifier.rotate(rotacionIconoDropDown)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactoListItem(
    modifier: Modifier = Modifier,
    contactoUiState: ContactoUiState,
    seleccionadoState: Boolean,
    onContactoClicked: (ContactoUiState) -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) = ElevatedCard(
    onClick = { onContactoClicked(contactoUiState) },
    modifier = modifier.then(
        Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    )
) {
    Column {
        ContenidoPrincipalCardContacto(
            contactoUiState = contactoUiState,
            seleccionadoState = seleccionadoState
        )
        if (seleccionadoState)
            AccionesContacto(
                onEditClicked = onEditClicked,
                onDeleteClicked = onDeleteClicked
            )
    }
}

@Preview(
    name = "PORTRAIT",
    device = "spec:width=360dp,height=800dp,dpi=480",
    showBackground = true
)
@Composable
fun ContactoListItemTest() {
    var contactoUiState by remember {
        mutableStateOf(
            ContactoUiState(
                nombre = "Andrea",
                apellidos = "García García",
                correo = "amdrea@gemail.com",
                telefono = "656288564",
                foto = null,
                categorias = CatergoriaUiState(
                    amigos = true,
                    familia = true
                )
            )
        )
    }
    var seleccionadoState by remember { mutableStateOf(false) }
    val onContactoClicked: (ContactoUiState) -> Unit = { seleccionadoState = !seleccionadoState }

    AgendaTheme {
        ContactoListItem(
            contactoUiState = contactoUiState,
            seleccionadoState = seleccionadoState,
            onContactoClicked = onContactoClicked,
            onEditClicked = {},
            onDeleteClicked = {}
        )
    }
}
