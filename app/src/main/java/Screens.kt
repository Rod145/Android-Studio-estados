import android.graphics.fonts.Font
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun tarjetatexto(modifier: Modifier= Modifier,button1: Boolean,button3: Boolean,valor: TextUnit,modoOscuro: Boolean,fuente2: Boolean){
    var espaciado= when{
        button1->1.0.sp
        button3->1.4.sp
        else-> 1.3.sp
    }
    var fuente= when{
        fuente2-> FontFamily.SansSerif
        else-> FontFamily.Serif
    }
    Text("VISTA PREVIA", color = Color.Black, fontSize = 13.sp)
    Spacer(modifier = Modifier.padding(5.dp))
    Card(modifier = modifier, colors = CardColors(when{modoOscuro->Color.Black;else->Color.White}, when{modoOscuro->Color.White;else->Color.Black}, Color.Unspecified,
        Color.Unspecified), border = BorderStroke
    (width = 1.dp, color = Color.Black)) {
        Column(modifier = Modifier.padding(16.dp)){Text("Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.", fontSize = valor, letterSpacing = espaciado, fontFamily = fuente)
        }
    }
}
@Composable
fun inicioScreen(modifier: Modifier= Modifier,viewmodel:Rviewmodel= viewModel()){
    val state=viewmodel.uiState.collectAsState().value
    Column(modifier = modifier.padding(16.dp)) {
        Text("<- Ajustes de lectura", color = Color.Black, fontSize = 22.sp)
        Spacer(modifier= Modifier.padding(top= 20.dp))
        Row{
            Text("tamaño de letra", color = Color.Black, fontSize = 17.sp)
            Spacer(modifier = Modifier.padding(start = 180.dp))
            Text("${state.valor.toInt()}"+" sp", color = Color.Black, fontSize = 17.sp)
        }
        Slider(value = state.valor, onValueChange = {viewmodel.valor(it)}, valueRange = 14f..32f, modifier = Modifier.scale(1f,0.8f),)
        Text("interlineado", color = Color.Black, fontSize = 19.sp)
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(selected=state.rad1, onClick = {viewmodel.rad1(true);viewmodel.rad2(false);viewmodel.rad3(false)})
            Text("Compacto",color=Color.Black)
            RadioButton(selected=state.rad2, onClick = {viewmodel.rad1(false);viewmodel.rad2(true);viewmodel.rad3(false)})
            Text("Normal", color = Color.Black)
            RadioButton(selected=state.rad3, onClick = {viewmodel.rad1(false);viewmodel.rad2(false);viewmodel.rad3(true)})
            Text("Amplio",color=Color.Black)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Modo oscuro", color = Color.Black, fontSize = 17.sp)
            Spacer(modifier=Modifier.weight(1f))
            Switch(checked = state.modoOscuro,{viewmodel.modoOscuro(it)})
        }
        Row{
            Text("Fuente", color = Color.Black, fontSize = 17.sp)
            Spacer(modifier = Modifier.weight(1f))
            FilterChip(selected = state.fuente1,{viewmodel.fuente1(true);viewmodel.fuente2(false)}, label = {Text("Serif",color=Color.Black)})
            Spacer(modifier= Modifier.padding(start = 7.dp))
            FilterChip(selected = state.fuente2,{viewmodel.fuente1(false);viewmodel.fuente2(true)}, label = {Text("Sans",color=Color.Black)})
        }
        tarjetatexto(modifier = Modifier.fillMaxWidth().height(300.dp), button1 = state.rad1, button3 = state.rad3,valor = state.valor.sp, modoOscuro= state.modoOscuro, fuente2 = state.fuente2)
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = {viewmodel.valor(22f);viewmodel.rad1(false);viewmodel.rad2(true);viewmodel.rad3(false);viewmodel.modoOscuro(true);viewmodel.fuente1(true);viewmodel.fuente2(false)}, modifier = Modifier.fillMaxWidth().height(50.dp)) {Text("Restablecer", color = Color.Black) }
    }
}
@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}