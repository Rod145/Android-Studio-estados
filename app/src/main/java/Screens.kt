import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun e(to: String){
    var to=to.toIntOrNull()
    if (to==null){return}
    for (i in 1..to){
        if (i>20){
            return
        }
        Text("R")
    }
}
@Composable
fun inicioScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        var texto by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(value = texto, onValueChange = {texto = it}, shape = RoundedCornerShape(20.dp),modifier = Modifier.height(60.dp).width(100.dp))
        e(texto)
    }
}
@Composable
@Preview
fun pr(){
}