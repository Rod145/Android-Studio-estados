import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun inicioScreen(modifier: Modifier = Modifier.fillMaxSize().background(Color.White), miclick:()->Unit){
    Column(modifier = modifier) {
            var texto by remember { mutableStateOf("") }
            var banderaT by remember { mutableStateOf(true)}
            TextField(value = texto, onValueChange = {texto = it}, readOnly = true)
            Row{
                Button(onClick = {texto=texto+"7"}) {Text("7")}
                Button(onClick = {texto=texto+"8"}) {Text("8")}
                Button(onClick = {texto=texto+"9"}) {Text("9")}
                Button(onClick = {if (banderaT){
                banderaT=false
                }else{
                    if()
                }
                    texto=texto+"-"}) {Text("-")}
            }
        Row{
            Button(onClick = {texto=texto+"4"}) {Text("4") }
            Button(onClick = {texto=texto+"5"}) {Text("5") }
            Button(onClick = {texto=texto+"6"}) {Text("6") }
            Button(onClick = {texto=texto+"+"}) {Text("+")}

        }
        Row{
            Button(onClick = {texto=texto+"1"}) {Text("1")}
            Button(onClick = {texto=texto+"2"}) {Text("2")}
            Button(onClick = {texto=texto+"3"}) {Text("3")}
            Button(onClick = {texto=texto+"X"}) {Text("X")}

        }

        Row{

            Button(onClick = {texto=texto+"0"}) {Text("0") }
            Button(onClick = {texto=texto+"."}) {Text(".") }
            Button(onClick = {

            }) {Text("=") }
            Button(onClick = {texto=""}) {Text("/") }
        }
        }
    }

@Composable
@Preview
fun pr(){
    inicioScreen(miclick = {})
}