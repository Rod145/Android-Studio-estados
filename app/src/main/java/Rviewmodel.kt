import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val correo: String = "",
    val contraseña: String = "",
    val ver: Boolean = false,
    val recordar: Boolean = false,
    val intentos: Int = 0,
    val incorrecto: Boolean = false
)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()

    fun correo(texto: String) {
        _uiState.update { it.copy(correo = texto) }
    }
    fun contraseña(texto: String) {
        _uiState.update { it.copy(contraseña = texto) }
    }
    fun ver(bol: Boolean) {
        _uiState.update { it.copy(ver = bol) }
    }
    fun recordar(bol: Boolean) {
        _uiState.update { it.copy(recordar = bol) }
    }
    fun intentos(entero: Int) {
        _uiState.update { it.copy(intentos = entero) }
    }
    fun incorrecto(bol: Boolean) {
        _uiState.update { it.copy(incorrecto = bol) }
    }
}