import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.pmdm.agenda.models.Contacto
import java.util.EnumSet
import com.pmdm.agenda.R

@Composable
private fun Contacto.Categorias.catergoriaUiStateIconPaninter() : Painter = when(this) {
    Contacto.Categorias.Amigos -> painterResource(R.drawable.sports_esports_24px)
    Contacto.Categorias.Trabajo -> painterResource(R.drawable.work_24px)
    Contacto.Categorias.Familia -> painterResource(R.drawable.family_restroom_24px)
    Contacto.Categorias.Emergencias -> painterResource(R.drawable.medical_services_24px)
}

@Composable
fun CatergoriaUiState.amigosIcon() = Contacto.Categorias.Amigos.catergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.trabajoIcon() = Contacto.Categorias.Trabajo.catergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.familiaIcon() = Contacto.Categorias.Familia.catergoriaUiStateIconPaninter()
@Composable
fun CatergoriaUiState.emergenciasIcon() = Contacto.Categorias.Emergencias.catergoriaUiStateIconPaninter()

data class CatergoriaUiState(
    val amigos: Boolean = false,
    val trabajo: Boolean = false,
    val familia: Boolean = false,
    val emergencias: Boolean = false,
)

fun CatergoriaUiState.toEnum() = EnumSet.noneOf(Contacto.Categorias::class.java).apply {
    if (amigos) add(Contacto.Categorias.Amigos)
    if (trabajo) add(Contacto.Categorias.Trabajo)
    if (familia) add(Contacto.Categorias.Familia)
    if (emergencias) add(Contacto.Categorias.Emergencias)
}