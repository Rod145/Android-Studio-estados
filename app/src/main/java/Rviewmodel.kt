import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val texto: String="")
class Rviewmodel: ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uiState=_uiState.asStateFlow()
    fun texto1(texto: String){
        _uiState.update { it.copy(texto = texto) }
    }

}