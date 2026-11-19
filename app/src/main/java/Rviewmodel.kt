import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val tareas: List<Tarea> = emptyList(),
    val alta: Boolean = true,
    val media: Boolean = false,
    val baja: Boolean = false,
    val todos: Boolean = true,
    val pendientes: Boolean = false,
    val hechas: Boolean = false,
    val confirmar: Boolean = false,
    val valor: String = ""
)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()

    fun tareas(lista: List<Tarea>) {
        _uiState.update { it.copy(tareas = lista) }
    }
    fun alta(bol: Boolean) {
        _uiState.update { it.copy(alta = bol) }
    }
    fun media(bol: Boolean) {
        _uiState.update { it.copy(media = bol) }
    }
    fun baja(bol: Boolean) {
        _uiState.update { it.copy(baja = bol) }
    }
    fun todos(bol: Boolean) {
        _uiState.update { it.copy(todos = bol) }
    }
    fun pendientes(bol: Boolean) {
        _uiState.update { it.copy(pendientes = bol) }
    }
    fun hechas(bol: Boolean) {
        _uiState.update { it.copy(hechas = bol) }
    }
    fun confirmar(bol: Boolean) {
        _uiState.update { it.copy(confirmar = bol) }
    }
    fun valor(texto: String) {
        _uiState.update { it.copy(valor = texto) }
    }
}