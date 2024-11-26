import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pmdm.agenda.data.ContactoRepository
import com.pmdm.agenda.models.Contacto
import com.pmdm.agenda.ui.features.ContactoUiState
import com.pmdm.agenda.ui.features.toContacto
import com.pmdm.agenda.ui.features.toContactoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactoViewModel @Inject constructor(
    // Inyectamos el repositorio y el validador de contactos
    private val contactoRepository: ContactoRepository,
    private val validadorContacto: ValidadorContacto
) : ViewModel() {
    class ContactoViewModelException(message: String) : Exception(message)

    // Este estado me indicará si estoy editando un contacto existente o creando uno nuevo
    var editandoContactoExistenteState: Boolean = false
        private set

    // Estados para el contacto y su validación. Solo son modificables desde el ViewModel
    var contactoState by mutableStateOf(ContactoUiState())
        private set
    var validacionContactoState by mutableStateOf(ValidacionContactoUiState())
        private set
    var verSnackBarState by mutableStateOf(false)
        private set

    // Este método se llamará cuando queramos editar un contacto ya existente
    // justo antes de navegar o cargar el formulario FormContactoScreen.
    // Recibimos el id del contacto a editar y lo buscamos en el repositorio.
    fun setContactoState(idContacto: Int) {
        // Indicamos que estamos editando un contacto existente, para hacer un update al guardar.
        editandoContactoExistenteState = true
        val c: Contacto = contactoRepository.get(idContacto)
            ?: throw ContactoViewModelException("El id $idContacto no existe en la base de datos")
        // Tras buscarlo en el repositorio, lo convertimos a ContactoUiState y lo asignamos al estado.
        contactoState = c.toContactoUiState()
        // Se actualiza el estado de validación, aunque no debería haber errores.
        validacionContactoState = validadorContacto.valida(contactoState)
    }

    // Este método se llamará cuando queramos crear un nuevo contacto.
    // O borrar su estado.
    fun clearContactoState() {
        editandoContactoExistenteState = false
        contactoState = ContactoUiState()
        validacionContactoState = ValidacionContactoUiState()
    }

    fun onContactoEvent(e: ContactoEvent) {
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
                // Validamos todo el contacto
                validacionContactoState = validadorContacto.valida(contactoState)
                // Si no hay errores, lo guardamos o actualizamos en el repositorio
                if (!validacionContactoState.hayError) {
                    val c: Contacto = contactoState.toContacto()
                    if (editandoContactoExistenteState) {
                        contactoRepository.update(c)
                    } else {
                        contactoRepository.insert(c)
                    }
                    // Tras guardar seguiremos iremos a la pantalla correspondiente
                    // por ahora no hacemos nada
                    e.onNavigateTrasFormContacto(true)
                } else {
                    // Si hay errores, mostramos el snackbar
                    verSnackBarState = true
                }
            }
        }
    }
}
