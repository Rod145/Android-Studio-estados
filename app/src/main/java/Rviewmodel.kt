import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val cal1: Double = 0.0,
    val cal2: Double = 0.0,
    val cal3: Double = 0.0,
    val valor: String = "",
    val familiar: Boolean = false,
    val economico: Boolean = false,
    val rapido: Boolean = false,
    val romantico: Boolean = false,
    val vegetariano: Boolean = false
)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()

    fun cal1(num: Double) {
        _uiState.update { it.copy(cal1 = num) }
    }
    fun cal2(num: Double) {
        _uiState.update { it.copy(cal2 = num) }
    }
    fun cal3(num: Double) {
        _uiState.update { it.copy(cal3 = num) }
    }
    fun valor(texto: String) {
        _uiState.update { it.copy(valor = texto) }
    }
    fun familiar(bol: Boolean) {
        _uiState.update { it.copy(familiar = bol) }
    }
    fun economico(bol: Boolean) {
        _uiState.update { it.copy(economico = bol) }
    }
    fun rapido(bol: Boolean) {
        _uiState.update { it.copy(rapido = bol) }
    }
    fun romantico(bol: Boolean) {
        _uiState.update { it.copy(romantico = bol) }
    }
    fun vegetariano(bol: Boolean) {
        _uiState.update { it.copy(vegetariano = bol) }
    }
}