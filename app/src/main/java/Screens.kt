import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun inicioScreen(modifier: Modifier = Modifier, numero1: Int,numero2: Int,miclick:()->Unit){
    Column(modifier = modifier) {
        Text(numero1.toString(), fontSize = 50.sp)
        Button(onClick = miclick) {
            Text("Sumar")
            var texto by remember { mutableStateOf("") }
            TextField(value = texto, onValueChange = {texto = it})

        }
    }
}
@Composable
@Preview
fun pr(){
}