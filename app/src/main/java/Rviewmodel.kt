import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val cantidad1:Int=0,val cantidad2:Int=0,val cantidad3:Int=0,val cantidad4:Int=0, val total: Double=0.0)
class Rviewmodel: ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uiState=_uiState.asStateFlow()
    fun cantidad1(entero: Int){
        _uiState.update { it.copy(cantidad1 = entero) }
    }
    fun cantidad2(entero: Int){
        _uiState.update { it.copy(cantidad2 = entero) }
    }
    fun cantidad3(entero: Int){
        _uiState.update { it.copy(cantidad3 = entero) }
    }
    fun cantidad4(entero: Int){
        _uiState.update { it.copy(cantidad4 = entero) }
    }
    fun total(tot: Double){
        _uiState.update { it.copy(total = tot) }
    }
}