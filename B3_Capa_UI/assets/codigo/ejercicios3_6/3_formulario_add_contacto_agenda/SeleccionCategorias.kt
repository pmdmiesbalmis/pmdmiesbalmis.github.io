import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.github.pmdmiesbalmis.components.ui.composables.FilterChipWithIcon
import com.pmdm.agenda.ui.features.CatergoriaUiState
import com.pmdm.agenda.ui.features.amigosIcon
import com.pmdm.agenda.ui.features.emergenciasIcon
import com.pmdm.agenda.ui.features.familiaIcon
import com.pmdm.agenda.ui.features.trabajoIcon
import com.pmdm.agenda.ui.theme.AgendaTheme

@Composable
fun SeleccionCategoriasConCheckBox(
    modifier: Modifier = Modifier,
    etiquetaGrupoState: String,
    categoriaState: CatergoriaUiState,
    onCategoriaChanged: (CatergoriaUiState) -> Unit
) {
    data class CheckBoxUiState(
        val label: String = "",
        val selected: Boolean = false,
        val icon: Painter? = null,
        val onClick: (Boolean) -> Unit = {}
    )

    val contenido = listOf(
        CheckBoxUiState(
            label = "Familia",
            selected = categoriaState.familia,
            icon = categoriaState.familiaIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(familia = it)) }
        ),
        CheckBoxUiState(
            label = "Amigos",
            selected = categoriaState.amigos,
            icon = categoriaState.amigosIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(amigos = it)) }
        ),
        CheckBoxUiState(
            label = "Trabajo",
            selected = categoriaState.trabajo,
            icon = categoriaState.trabajoIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(trabajo = it)) }
        ),
        CheckBoxUiState(
            label = "Emergencias",
            selected = categoriaState.emergencias,
            icon = categoriaState.emergenciasIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(emergencias = it)) }
        )
    )

    Column(modifier = modifier) {
        Text(text = etiquetaGrupoState)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(all = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(contenido) { item ->
                CheckboxWithLabel(
                    label = item.label,
                    checkedState = item.selected,
                    enabledState = true,
                    onStateChange = item.onClick
                )
            }
        }
    }
}

@Composable
fun SeleccionCategoriasConFilterChip(
    modifier: Modifier = Modifier,
    etiquetaGrupoState: String? = null,
    categoriaState: CatergoriaUiState,
    onCategoriaChanged: (CatergoriaUiState) -> Unit
) {
    data class FilterChipGroupUiState(
        val label: String = "",
        val selected: Boolean = false,
        val icon: Painter? = null,
        val onClick: () -> Unit = {}
    )

    val contenido = listOf(
        FilterChipGroupUiState(
            label = "Familia",
            selected = categoriaState.familia,
            icon = categoriaState.familiaIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(familia = !categoriaState.familia)) }
        ),
        FilterChipGroupUiState(
            label = "Amigos",
            selected = categoriaState.amigos,
            icon = categoriaState.amigosIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(amigos = !categoriaState.amigos)) }
        ),
        FilterChipGroupUiState(
            label = "Trabajo",
            selected = categoriaState.trabajo,
            icon = categoriaState.trabajoIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(trabajo = !categoriaState.trabajo)) }
        ),
        FilterChipGroupUiState(
            label = "Emergencias",
            selected = categoriaState.emergencias,
            icon = categoriaState.emergenciasIcon(),
            onClick = { onCategoriaChanged(categoriaState.copy(emergencias = !categoriaState.emergencias)) }
        )
    )

    Column(modifier = modifier) {
        if (etiquetaGrupoState != null)
            Text(text = etiquetaGrupoState)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(130.dp),
            contentPadding = PaddingValues(all = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(contenido) { item ->
                FilterChipWithIcon(
                    textoState = item.label,
                    seleccionadoState = item.selected,
                    iconState = item.icon,
                    onClick = item.onClick
                )
            }
        }
    }
}

@Preview(
    name = "PORTRAIT",
    device = "spec:width=300dp,height=800dp,dpi=480",
    showBackground = true
)
@Preview(
    name = "LANDSCAPE",
    device = "spec:width=360dp,height=800dp,dpi=480,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, fontScale = 1.0f
)
@Composable
fun SeleccionCategoriasTest() {
    var categoria by remember {
        mutableStateOf(
            CatergoriaUiState()
        )
    }

    AgendaTheme {
        Surface {
            Column {
                SeleccionCategoriasConCheckBox(
                    etiquetaGrupoState = "Categorizar como:",
                    categoriaState = categoria,
                    onCategoriaChanged = { categoria = it }
                )
                SeleccionCategoriasConFilterChip(
                    etiquetaGrupoState = "Categorizar como:",
                    categoriaState = categoria,
                    onCategoriaChanged = { categoria = it }
                )
            }
        }
    }
}

