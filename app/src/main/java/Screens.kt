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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun cajaTarea(modifier: Modifier= Modifier,tarea: Tarea,onclick1:()-> Unit){
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
            Checkbox(checked = tarea.eliminar,onCheckedChange = {tarea.eliminar= it} )
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inicioScreen(modifier: Modifier= Modifier){
    var tareas =remember { mutableStateListOf<Tarea>() }
    var e by remember { mutableStateOf( 0) }
    var alta by remember { mutableStateOf(true) }
    var media by remember { mutableStateOf(false) }
    var baja by remember { mutableStateOf(false) }
    var todos by remember { mutableStateOf(true) }
    var pendientes by remember { mutableStateOf(false) }
    var hechas by remember { mutableStateOf(false) }
    var etiqueta= when{
        alta->"Alta"
        media->"Media"
        baja->"Baja"
        else->"brr"
    }
    var prioridad=when{
        todos->"Todos"
        pendientes->"Pendientes"
        hechas->"Hechas"
        else->{}
    }
    val pendientesf=tareas.count{!it.eliminar}
    var confirmar by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(16.dp)) {
        Text("Mis pendientes", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        var valor by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row( verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()){
            OutlinedTextField(value = valor, onValueChange = {valor=it}, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = {Text("Nueva tarea", color = Color.Gray)})
            Spacer(Modifier.width(10.dp))
            Button(onClick = {if(valor.isNotBlank()){tareas.add(Tarea(valor,etiqueta=etiqueta))}}, contentPadding = PaddingValues.Zero, shape = RoundedCornerShape(20.dp), modifier = Modifier.size(57.dp)) {Text("+", fontSize = 30.sp) }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Prioridad", color = Color.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = alta, onClick = {alta=true;media=false;baja=false}, label = {Text("Alta",color= Color.Black)})
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = media, onClick = {alta=false;media=true;baja=false}, label = {Text("Media",color= Color.Black)})
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = baja, onClick = {alta=false;media=false;baja=true}, label = {Text("Baja",color= Color.Black)})

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = todos, onClick = {todos=true;pendientes=false;hechas=false}, label = {Text("Todas",color= Color.Black)})
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = pendientes, onClick = {todos=false;pendientes=true;hechas=false}, label = {Text("Pendientes",color= Color.Black)})
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = hechas, onClick = {todos=false;pendientes=false;hechas=true}, label = {Text("Hechas",color= Color.Black)})
        }
        Spacer(modifier=Modifier.padding(top = 10.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier=Modifier.padding(top = 10.dp))
        LazyColumn(modifier=Modifier.heightIn(max = 300.dp)) {
            items(tareas) {tarea->
                when(prioridad){
                    "Todos"->cajaTarea(tarea = tarea) {tareas.remove(tarea)}
                    "Pendientes"->if (!tarea.eliminar){cajaTarea(tarea = tarea) {tareas.remove(tarea)}}
                    "Hechas"->if (tarea.eliminar){cajaTarea(tarea = tarea) {tareas.remove(tarea)}}
                    else->{}
                }

            }
        }
        if (tareas.lastIndex!=-1){
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Text("$pendientesf  pendientes de ${tareas.size}", color = Color.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(top = 40.dp))
            OutlinedButton(onClick = { confirmar=true},modifier=Modifier.fillMaxWidth().height(50.dp)) {Text("Borrar completadas", color = Color.Black) }
            if(confirmar){AlertDialog(containerColor = Color(0xF2EEEEEE),onDismissRequest = {confirmar=false}, text = {Text("¿deseas borrar las tareas seleccionadas?",color=Color.Black, fontSize = 18.sp, textAlign = TextAlign.Center)}, confirmButton = { Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){Button(onClick = {confirmar=false;tareas.removeAll { it.eliminar }}, content = {Text("confirmar", color = Color.Black)});Button(onClick = {confirmar=false}, content = {Text("Cancelar", color = Color.Black)}); }})}
        }

    }
}
@Composable
@Preview
fun pr(){
inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))}