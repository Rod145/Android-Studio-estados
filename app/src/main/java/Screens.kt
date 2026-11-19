import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class Usuario(var correo: String = "Fedep@gmail.com", var contraseña: String = "diceola")
var us = Usuario()

@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel: Rviewmodel = viewModel(), clicko: (Int) -> Unit) {
    val state = viewmodel.uiState.collectAsState().value
    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.size(60.dp).clip(CircleShape).background(Color(0xFFD8DBDC)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text("Clínica Vida", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text("Consulta tus citas medicas", fontWeight = FontWeight.Bold, color = Color.Gray)
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = state.correo, onValueChange = { viewmodel.correo(it) }, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = { Text("Correo", color = Color.Black) })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = state.contraseña, visualTransformation = if (!state.ver) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }, onValueChange = { viewmodel.contraseña(it) }, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = { Text("Contraseña", color = Color.Black) }, trailingIcon = { FilterChip(modifier = Modifier.padding(end = 10.dp), selected = state.ver, onClick = { viewmodel.ver(!state.ver) }, label = { Text(if (state.ver) { "Ocultar" } else { "mostrar" }, color = Color.Black) }) })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state.recordar, onCheckedChange = { viewmodel.recordar(it) })
            Text("Recordarme", fontSize = 18.sp, color = Color.Black)
        }
        if (state.incorrecto) {
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(Color(0xFFFADDDD))) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Correo o contraseña incorrectos", fontSize = 18.sp, color = Color(0xFF913434))
                    Text("(intento ${state.intentos} de 3)", fontSize = 12.sp, color = Color(0xFFAB5252))
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xC6BDB8B8)), enabled = state.intentos != 3, onClick = {
            if (state.correo == us.correo && state.contraseña == us.contraseña) clicko(2)
            else {
                viewmodel.intentos(state.intentos + 1)
                when {
                    state.intentos + 1 >= 1 -> viewmodel.incorrecto(true)
                    else -> viewmodel.incorrecto(false)
                }
            }
        }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("Entrar", color = Color.White)
        }
    }
}
