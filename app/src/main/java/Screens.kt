import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun inicioScreen(modifier: Modifier= Modifier){
    var seleccion1 by remember { mutableStateOf(false) }
    var seleccion2 by remember { mutableStateOf(false) }
    var seleccion3 by remember { mutableStateOf(false) }
    var seleccion4 by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(16.dp)) {
        var valor by remember { mutableStateOf("") }
        OutlinedTextField(value = valor, onValueChange = {valor=it},label ={ Text("Monto de la cuenta",color = Color.Black) } )
        Spacer(modifier=Modifier.padding(top = 15.dp))
        Text("Propina", color = Color.Black)
        Row {
            FilterChip(selected = seleccion1, onClick = {seleccion1=true;seleccion2=false;seleccion3=false;seleccion4=false;}, label = {Text("10%", color = Color.Black)})
            FilterChip(selected = seleccion2, onClick = {seleccion2=true;seleccion1=false;seleccion3=false;seleccion4=false;}, label = {Text("15%", color = Color.Black)},
                modifier=Modifier.padding(start = 5.dp))
            FilterChip(selected = seleccion3, onClick = {seleccion3=true;seleccion1=false;seleccion2=false;seleccion4=false;}, label = {Text("10%", color = Color.Black)},
                modifier=Modifier.padding(start = 5.dp))
            FilterChip(selected = seleccion4, onClick = {seleccion4=true;seleccion1=false;seleccion2=false;seleccion3=false;}, label = {Text("Otro", color = Color.Black)},
                modifier=Modifier.padding(start = 5.dp))
        }
        Spacer(modifier=Modifier.padding(top = 15.dp))
        Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Personas", color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(onClick = {}, shape = CircleShape,modifier = Modifier.size(40.dp), contentPadding = PaddingValues(0.dp)) {Text("-", fontSize = 30.sp) }

        }

    }
}
@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}