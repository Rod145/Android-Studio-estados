import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val personas: Int=0, val visitasdaily: Int=0)
class Rviewmodel: ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uiState=_uiState.asStateFlow()
    fun personas(entero:Int){
        _uiState.update { it.copy(personas = entero) }
    }
    fun visitasdaily(entero:Int){
        _uiState.update { it.copy(visitasdaily = entero) }
    }
}