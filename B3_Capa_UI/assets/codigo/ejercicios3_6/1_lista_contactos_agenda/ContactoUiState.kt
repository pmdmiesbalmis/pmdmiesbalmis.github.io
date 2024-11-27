import androidx.compose.ui.graphics.ImageBitmap
import com.pmdm.agenda.models.Contacto
import java.time.Instant
import com.github.pmdmiesbalmis.utilities.imagetools.*

data class ContactoUiState(
    val id: Int = Instant.now().epochSecond.toInt(),
    val nombre: String = "",
    val apellidos: String = "",
    val foto: ImageBitmap? = null,
    val correo: String = "",
    val telefono: String = "",
    val categorias : CatergoriaUiState = CatergoriaUiState(),
)

fun ContactoUiState.toContacto() = Contacto(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    correo = correo,
    telefono = telefono,
    foto = foto?.toBase64(),
    categorias = categorias.toEnum()
)

fun Contacto.toContactoUiState() = ContactoUiState(
    id = id,
    nombre = nombre,
    apellidos = apellidos,
    correo = correo,
    telefono = telefono,
    foto = foto?.base64ToImageBitmap(),
    categorias = CatergoriaUiState(
        amigos = categorias.contains(Contacto.Categorias.Amigos),
        trabajo = categorias.contains(Contacto.Categorias.Trabajo),
        familia = categorias.contains(Contacto.Categorias.Familia),
        emergencias = categorias.contains(Contacto.Categorias.Emergencias))
)