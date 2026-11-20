import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class Pantalla { Inicio, Pregunta, Resultados }

data class MutableObject(
    val pantalla: Pantalla = Pantalla.Inicio,
    val nombre: String = "",
    val indice: Int = 0,
    val puntaje: Int = 0,
    val seleccion: Int = -1,
    val confirmado: Boolean = false
)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()

    fun pantalla(valor: Pantalla) { _uiState.update { it.copy(pantalla = valor) } }
    fun nombre(texto: String) { _uiState.update { it.copy(nombre = texto) } }
    fun indice(entero: Int) { _uiState.update { it.copy(indice = entero) } }
    fun puntaje(entero: Int) { _uiState.update { it.copy(puntaje = entero) } }
    fun seleccion(entero: Int) { _uiState.update { it.copy(seleccion = entero) } }
    fun confirmado(bol: Boolean) { _uiState.update { it.copy(confirmado = bol) } }
}