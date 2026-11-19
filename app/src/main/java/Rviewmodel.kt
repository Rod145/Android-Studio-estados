import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val text1: String ="", val text2: String ="", var resultado: Int =0)
class Rviewmodel: ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uiState= _uiState.asStateFlow()
    fun text1(text: String){
        _uiState.update { it.copy(text1 = text) }
    }
    fun text2(text: String){
        _uiState.update { it.copy(text2 = text) }
    }
    fun resultado(){
        if (uiState.value.text1!=""||uiState.value.text2!="") {
            _uiState.update { it.copy(resultado = it.text1.toInt()+it.text2.toInt()) }
        }
    }
}