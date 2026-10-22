import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Tarjeta(var nombre: String, var apellido: String)
@Composable
fun mycard(tarjeta: Tarjeta,ola=){
    Card(modifier = Modifier.width(190.dp).height(40.dp)){
        Row(modifier=Modifier.padding(6.dp)){
            Text(text = tarjeta.nombre, fontSize = 20.sp)
            Spacer(modifier= Modifier.padding(10.dp))
            Text(text = tarjeta.apellido, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(2.dp))
            Button(onClick = = Modifier.height(25.dp).width(25.dp).background(Color.Gray))
        }
    }
}
@Composable
@Preview
fun prr(){
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        mycard(tarjeta =Tarjeta("rodrigo","pedrito"))

    }
}