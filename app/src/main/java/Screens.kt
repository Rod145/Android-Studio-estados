import android.R
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

@Composable
fun inicioScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        var texto by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(value = texto, onValueChange = {texto = it}, keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Number ), shape = RoundedCornerShape(20.dp), modifier = Modifier.width(100.dp).height(
                55.dp))
        Spacer(modifier =  Modifier.padding(10.dp))
        var texto2 by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(value = texto2, onValueChange = {texto2 = it}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), shape = RoundedCornerShape(20.dp), modifier = Modifier.width(100.dp).height(
            55.dp))
        Spacer(modifier =  Modifier.padding(10.dp))
        var resultado by rememberSaveable{mutableStateOf(0)}
        Button(modifier = Modifier.width(100.dp).height(50.dp),onClick = {
            if (texto==""||texto2==""){
                return@Button
            }
            resultado=texto.toInt()+texto2.toInt()
        }) {
            Text("Sumar")
        }
        Text(text= "$resultado", fontSize = 20.sp)
    }
}
@Composable
@Preview
fun pr(){
}