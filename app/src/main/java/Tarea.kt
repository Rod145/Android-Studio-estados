import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
class Tarea(var pendiente:String, var etiqueta: String) {
    var eliminar by mutableStateOf(false)
}