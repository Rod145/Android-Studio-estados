import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val gastos: List<Gasto> = emptyList(),
    val ingresoTexto: String = "4500",
    val concepto: String = "",
    val monto: String = "",
    val categoria: String = "Comida",
    val expandido: Boolean = false
)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()

    fun gastos(lista: List<Gasto>) {
        _uiState.update { it.copy(gastos = lista) }
    }
    fun ingresoTexto(texto: String) {
        _uiState.update { it.copy(ingresoTexto = texto) }
    }
    fun concepto(texto: String) {
        _uiState.update { it.copy(concepto = texto) }
    }
    fun monto(texto: String) {
        _uiState.update { it.copy(monto = texto) }
    }
    fun categoria(texto: String) {
        _uiState.update { it.copy(categoria = texto) }
    }
    fun expandido(bol: Boolean) {
        _uiState.update { it.copy(expandido = bol) }
    }
}