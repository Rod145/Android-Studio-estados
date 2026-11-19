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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun cajaTarea(modifier: Modifier = Modifier, tarea: Tarea, onCheck: (Boolean) -> Unit, onclick1: () -> Unit) {
    var color1 = when (tarea.etiqueta) {
        "Alta" -> Color(0xFFF7DCDA)
        "Media" -> Color(0xFFFDEEC0)
        else -> Color(0xFFDBEDD7) //Baja
    }
    var color2 = when (tarea.etiqueta) {
        "Alta" -> Color(0xFFFF1744)
        "Media" -> Color(0xFFA66704)
        else -> Color(0xFF1FA901) //Baja
    }
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = tarea.eliminar, onCheckedChange = onCheck)
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Text(tarea.pendiente, color = Color.Black, fontSize = 18.sp, textDecoration = if (tarea.eliminar) { TextDecoration.LineThrough } else { TextDecoration.None })
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(onClick = onclick1, shape = CircleShape, contentPadding = PaddingValues.Zero, modifier = Modifier.size(30.dp)) { Text("X", color = Color.Gray) }
        }
        Box(modifier = Modifier.offset(60.dp, (-10).dp).clip(RoundedCornerShape(20.dp)).background(color1).width(if (tarea.etiqueta == "Media") { 55.dp } else { 45.dp }).height(25.dp), contentAlignment = Alignment.Center) { Text(tarea.etiqueta, color = color2) }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel: Rviewmodel = viewModel()) {
    val state = viewmodel.uiState.collectAsState().value
    var etiqueta = when {
        state.alta -> "Alta"
        state.media -> "Media"
        state.baja -> "Baja"
        else -> "brr"
    }
    var prioridad = when {
        state.todos -> "Todos"
        state.pendientes -> "Pendientes"
        state.hechas -> "Hechas"
        else -> {}
    }
    val pendientesf = state.tareas.count { !it.eliminar }
    Column(modifier = modifier.padding(16.dp)) {
        Text("Mis pendientes", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = state.valor, onValueChange = { viewmodel.valor(it) }, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = { Text("Nueva tarea", color = Color.Gray) })
            Spacer(Modifier.width(10.dp))
            Button(onClick = { if (state.valor.isNotBlank()) { viewmodel.tareas(state.tareas + Tarea(state.valor, etiqueta = etiqueta)) } }, contentPadding = PaddingValues.Zero, shape = RoundedCornerShape(20.dp), modifier = Modifier.size(57.dp)) { Text("+", fontSize = 30.sp) }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Prioridad", color = Color.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.alta, onClick = { viewmodel.alta(true); viewmodel.media(false); viewmodel.baja(false) }, label = { Text("Alta", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.media, onClick = { viewmodel.alta(false); viewmodel.media(true); viewmodel.baja(false) }, label = { Text("Media", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.baja, onClick = { viewmodel.alta(false); viewmodel.media(false); viewmodel.baja(true) }, label = { Text("Baja", color = Color.Black) })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.todos, onClick = { viewmodel.todos(true); viewmodel.pendientes(false); viewmodel.hechas(false) }, label = { Text("Todas", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.pendientes, onClick = { viewmodel.todos(false); viewmodel.pendientes(true); viewmodel.hechas(false) }, label = { Text("Pendientes", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(selected = state.hechas, onClick = { viewmodel.todos(false); viewmodel.pendientes(false); viewmodel.hechas(true) }, label = { Text("Hechas", color = Color.Black) })
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
            items(state.tareas) { tarea ->
                val onCheck: (Boolean) -> Unit = { check ->
                    viewmodel.tareas(state.tareas.map { if (it === tarea) it.copy(eliminar = check) else it })
                }
                val onEliminar: () -> Unit = {
                    viewmodel.tareas(state.tareas.filter { it !== tarea })
                }
                when (prioridad) {
                    "Todos" -> cajaTarea(tarea = tarea, onCheck = onCheck, onclick1 = onEliminar)
                    "Pendientes" -> if (!tarea.eliminar) { cajaTarea(tarea = tarea, onCheck = onCheck, onclick1 = onEliminar) }
                    "Hechas" -> if (tarea.eliminar) { cajaTarea(tarea = tarea, onCheck = onCheck, onclick1 = onEliminar) }
                    else -> {}
                }
            }
        }
        if (state.tareas.lastIndex != -1) {
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Text("$pendientesf  pendientes de ${state.tareas.size}", color = Color.Black, fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(top = 40.dp))
            OutlinedButton(onClick = { viewmodel.confirmar(true) }, modifier = Modifier.fillMaxWidth().height(50.dp)) { Text("Borrar completadas", color = Color.Black) }
            if (state.confirmar) {
                AlertDialog(containerColor = Color(0xF2EEEEEE), onDismissRequest = { viewmodel.confirmar(false) }, text = { Text("¿deseas borrar las tareas seleccionadas?", color = Color.Black, fontSize = 18.sp, textAlign = TextAlign.Center) }, confirmButton = {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = { viewmodel.confirmar(false); viewmodel.tareas(state.tareas.filter { !it.eliminar }) }, content = { Text("confirmar", color = Color.Black) })
                        Button(onClick = { viewmodel.confirmar(false) }, content = { Text("Cancelar", color = Color.Black) })
                    }
                })
            }
        }
    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}