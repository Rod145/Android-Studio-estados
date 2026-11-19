import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val rad1: Boolean = false,
    val rad2: Boolean = true,
    val rad3: Boolean = false,
    val valor: Float = 22f,
    val modoOscuro: Boolean = true,
    val fuente1: Boolean = true,
    val fuente2: Boolean = false
)

class Rviewmodel : ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uiState=_uiState.asStateFlow()
    fun rad1(rad: Boolean){
        _uiState.update { it.copy(rad1 = rad) }
    }
    fun rad2(rad: Boolean){
        _uiState.update { it.copy(rad2 = rad) }
    }
    fun rad3(rad: Boolean){
        _uiState.update { it.copy(rad3 = rad) }
    }
    fun valor(valor: Float){
        _uiState.update { it.copy(valor = valor) }
    }
    fun modoOscuro(modo: Boolean){
        _uiState.update { it.copy(modoOscuro = modo) }
    }
    fun fuente1(fuente: Boolean){
        _uiState.update { it.copy(fuente1 = fuente) }
    }
    fun fuente2(fuente: Boolean){
        _uiState.update { it.copy(fuente2 = fuente) }
    }
}