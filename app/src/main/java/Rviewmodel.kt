import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val valorS: Float = 0f,
    val redondeo: Boolean = false,
    val seleccion1: Boolean = false,
    val seleccion2: Boolean = false,
    val seleccion3: Boolean = false,
    val seleccion4: Boolean = false,
    val personas: Int = 1,
    val valor: String = "",
){
    val valido: Boolean get()  = valor.toDoubleOrNull() != null

}

class Rviewmodel : ViewModel() {
    private val _uitState = MutableStateFlow(MutableObject())
    val uiState = _uitState.asStateFlow()
    fun valorS(flot: Float){
        _uitState.update { it.copy(valorS=flot) }
    }
    fun redondeo(bol: Boolean){
        _uitState.update { it.copy(redondeo=bol) }
    }
    fun seleccion1(bol: Boolean){
        _uitState.update { it.copy(seleccion1=bol) }
    }
    fun seleccion2(bol: Boolean){
        _uitState.update { it.copy(seleccion2=bol) }
    }
    fun seleccion3(bol: Boolean){
        _uitState.update { it.copy(seleccion3=bol) }
    }
    fun seleccion4(bol: Boolean){
        _uitState.update { it.copy(seleccion4=bol) }
    }
    fun personas(entero: Int){
        _uitState.update { it.copy(personas=entero) }
    }
    fun valor(valor: String){
        _uitState.update { it.copy(valor=valor) }
    }
}