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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class Usuario(var correo: String="Fedep@gmail.com",var contraseña: String="diceola")
    var us=Usuario()

@Composable
fun inicioScreen(modifier: Modifier= Modifier,clicko:(Int)-> Unit){
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var ver by remember { mutableStateOf(false) }
    var recordar by remember { mutableStateOf(false) }
    var intentos by remember { mutableIntStateOf(0) }
    var incorrecto by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Column(modifier=Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
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
            Spacer(modifier= Modifier.padding(top = 10.dp))
            Text("Clínica Vida", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier= Modifier.padding(top = 10.dp))
            Text("Consulta tus citas medicas", fontWeight = FontWeight.Bold, color = Color.Gray)
        }
        Spacer(modifier= Modifier.padding(top = 20.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = correo, onValueChange = {correo=it}, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = {Text("Correo", color = Color.Black)})
        Spacer(modifier= Modifier.padding(top = 20.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = contraseña, visualTransformation = if(!ver){
            PasswordVisualTransformation()}else{
            VisualTransformation.None}, onValueChange = {contraseña=it}, colors = OutlinedTextFieldDefaults.colors(Color.Black), label = {Text("Contraseña", color = Color.Black )}, trailingIcon = { FilterChip(modifier = Modifier.padding(end = 10.dp),selected = ver, onClick = {ver=!ver}, label = {Text(if(ver){"Ocultar"}else{"mostrar"}, color = Color.Black)}) })
        Spacer(modifier= Modifier.padding(top = 20.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Checkbox(checked = recordar, onCheckedChange = {recordar=it})
            Text("Recordarme", fontSize = 18.sp, color = Color.Black)
        }
        if (incorrecto){Card(modifier=Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(Color(0xFFFADDDD))){
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Correo o contraseña incorrectos", fontSize = 18.sp, color = Color(0xFF913434))
                Text("(intento $intentos de 3)", fontSize = 12.sp, color = Color(0xFFAB5252))
            }
        }}
        Spacer(modifier= Modifier.padding(top = 20.dp))
        Button(colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xC6BDB8B8)),enabled = intentos != 3,onClick = {if (correo==us.correo&&contraseña==us.contraseña)clicko(2);else{intentos++;when{intentos>=1->incorrecto=true; else->incorrecto=false}} },modifier = Modifier.fillMaxWidth().height(50.dp)) {Text("Entrar", color = Color.White)
        }

    }
}
@Composable
fun pantalla2(modifier:Modifier= Modifier,clicko2:(Int)-> Unit){
    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(top = 50.dp))
        Column(modifier=Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
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
            Spacer(modifier= Modifier.padding(top = 10.dp))
            Text("Bienvenido", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier= Modifier.padding(top = 10.dp))
            Text("Inicio de seion exitoso", fontWeight = FontWeight.Bold, color = Color.Gray)
    }
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = us.correo, onValueChange = {}, enabled = false, colors = OutlinedTextFieldDefaults.colors(Color.Black, disabledTextColor = Color.Black), label = {Text("Correo", color = Color.Black)})
        Spacer(modifier = Modifier.padding(top=20.dp))
        Spacer(modifier = Modifier.padding(top=20.dp))
        Button(onClick = {clicko2(1)},modifier = Modifier.fillMaxWidth().height(50.dp)) {Text("Cerrar sesion", color = Color.White)}
        }
}
@Composable
@Preview
fun pr(){
    pantalla2(modifier = Modifier.fillMaxSize().background(Color.White),{})
}