import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val objeto: List<Tarjeta> = emptyList(), val texto1: String="", val texto2: String="")
class Rviewmodel: ViewModel() {
    private val _uiState= MutableStateFlow(MutableObject())
    val uitState=_uiState.asStateFlow()
    fun agregar(objeto:Tarjeta=Tarjeta(uitState.value.texto1,uitState.value.texto2)){
        if (uitState.value.texto1.isNotBlank()&&uitState.value.texto2.isNotBlank()){
            _uiState.update { it.copy(objeto = it.objeto+objeto) }
            _uiState.update { it.copy(texto1 = "") }
            _uiState.update { it.copy(texto2 = "") }
        }

    }

    fun eliminarTodo(){
        _uiState.update { it.copy(objeto = emptyList()) }
    }

    fun eliminar(objeto:Tarjeta){
        _uiState.update { it.copy(objeto = it.objeto-objeto) }
    }

    fun texto1(texto: String){
        _uiState.update { it.copy(texto1 =texto) }
    }
    fun texto2(texto: String){
        _uiState.update { it.copy(texto2 =texto) }
    }
}