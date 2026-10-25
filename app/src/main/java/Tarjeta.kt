import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Tarjeta(var nombre: String, var apellido: String, var color: Color)
@Composable
fun mycard(tarjeta: Tarjeta,miclick2:()->Unit){
    var ifcolorwhite= when(tarjeta.color){
        Color.White-> Color.Black
        else-> Color.White
    }
    Card(modifier = Modifier.width(200.dp).height(40.dp), colors = CardColors(tarjeta.color, ifcolorwhite,Color.White,
        Color.White), border = BorderStroke(color = ifcolorwhite, width = 1.dp)){
        Row{
            Row(modifier = Modifier.weight(1f).padding(6.dp)) {
                Text(text = tarjeta.nombre, fontSize = 20.sp)
                Spacer(modifier= Modifier.padding(2.dp))
                Text(text = tarjeta.apellido, fontSize = 20.sp)
                }
            ElevatedButton(onClick =miclick2,modifier = Modifier.height(20.dp).width(20.dp).offset(-10.dp,7.dp), colors = ButtonColors(ifcolorwhite,Color.Unspecified,Color.Unspecified,Color.Unspecified)) {

    }

        }
    }
}
@Composable
@Preview
fun prr(){
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        mycard(tarjeta =Tarjeta("rodrigo","pedrito",Color.White), miclick2 = {})

    }
}