import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MutableObject(val x: Int =0)
class Rviewmodel: ViewModel() {
    private val _uistate= MutableStateFlow(MutableObject())
    val uistate=_uistate.asStateFlow()
    fun aumentar(){
        _uistate.update { it.copy(x = it.x+1) }
    }
}