import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(
    val identificarGmail: Boolean = false,
    val identificarEdad: Boolean = false,
    val identificarNombre: Boolean = false,
    val identificarMarca: Boolean = true,
    val marcado: Boolean= false,
    val valor: String = "",
    val valor2: String = "",
    val valor3: String = "",
    val expandido: Boolean = false,
    val opciones: List<String> =listOf("Fotografia","Dibujo"),
    val seleccion: String = opciones.first()

)

class Rviewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(MutableObject())
    val uiState = _uiState.asStateFlow()
    fun identificarGmail(bol: Boolean) {
        _uiState.update { it.copy(identificarGmail = bol) }
    }

    fun identificarNombre(bol: Boolean) {
        _uiState.update { it.copy(identificarNombre = bol) }
    }
    fun identificarEdad(bol: Boolean) {
        _uiState.update { it.copy(identificarEdad = bol) }
    }

    fun identificarMarca(bol: Boolean) {
        _uiState.update { it.copy(identificarMarca = bol) }
    }
    fun marcado(bol: Boolean) {
        _uiState.update { it.copy(marcado = bol) }
    }

    fun valor(valor: String) {
        _uiState.update { it.copy(valor = valor) }
    }

    fun valor2(valor: String) {
        _uiState.update { it.copy(valor2 = valor) }
    }

    fun valor3(valor: String) {
        _uiState.update { it.copy(valor3 = valor) }
    }

    fun expandido(bol: Boolean) {
        _uiState.update { it.copy(expandido = bol) }
    }

    fun seleccion(lista: String) {
        _uiState.update { it.copy(seleccion = lista) }
    }
}