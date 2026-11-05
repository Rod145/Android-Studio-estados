import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun cajaTarea(modifier: Modifier= Modifier,tarea: Tarea,onclick1:()-> Unit){
    var ola by remember { mutableStateOf(false) }
    tarea.eliminar=ola
    var color1= when (tarea.etiqueta) {
        "Alta" -> Color(0xFFF7DCDA)
        "Media" -> Color(0xFFFDEEC0)
        else -> Color(0xFFDBEDD7) //Baja
    }
    var color2= when (tarea.etiqueta) {
        "Alta" -> Color(0xFFFF1744)
        "Media" -> Color(0xFFA66704)
        else -> Color(0xFF1FA901) //Baja
    }
    Column(modifier=modifier) {
        Row(modifier=Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = ola,onCheckedChange = {ola= it} )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Text(tarea.pendiente, color = Color.Black, fontSize = 18.sp, textDecoration = if (tarea.eliminar){ TextDecoration.LineThrough}else{ TextDecoration.None})
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(onClick = onclick1, shape = CircleShape, contentPadding = PaddingValues.Zero, modifier = Modifier.size(30.dp), ) { Text("X", color = Color.Gray)}
        }
        Box(modifier=Modifier.offset(60.dp, (-10).dp).clip(RoundedCornerShape(20.dp)).background(color1).width(if (tarea.etiqueta=="Media"){55.dp}else{45.dp}).height(25.dp), contentAlignment = Alignment.Center)  {Text(tarea.etiqueta, color = color2)}
        Spacer(modifier= Modifier.padding(top = 10.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
    }
}
@Composable
fun inicioScreen(modifier: Modifier= Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        var tareas =remember { mutableListOf<Tarea>() }
        LazyColumn(modifier=Modifier.fillMaxWidth()) {
            items(tareas,) {
                cajaTarea(tarea =tarea1, onclick1 = {})
            }
        }

    }
}
@Composable
@Preview
fun pr(){
inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))}