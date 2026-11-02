import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorTaller(opciones: List<String>) {
    var expandido by remember { mutableStateOf(false) }
    var seleccion by rememberSaveable { mutableStateOf(opciones.first()) }

    ExposedDropdownMenuBox(expanded = expandido, onExpandedChange = { expandido = it }) {
        OutlinedTextField(
            value = seleccion,
            onValueChange = {},
            readOnly = true,
            label = { Text("Taller") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandido) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            opciones.forEach {
                DropdownMenuItem(text = { Text(it) }, onClick = { seleccion = it; expandido = false })
            }
        }
    }
}
fun verificarArroba(texto:String): Boolean{
    if (texto.isBlank()){return true}
for (i in texto.indices){
    if(texto[i]=='@'){
        if(texto.substring(i..<texto.length)=="@gmail.com"){
            return false;
        }
    } }
    return true
}
fun verificarNombre(texto:String): Boolean{
    if (texto.isBlank()){return true}else if (texto.length>=3){return false}
return true
}
fun verificarEdad(texto:String): Boolean{
    if (texto.isBlank()||texto.toInt()>99){return true}else if (texto.toInt()>13){
        return false
    }
    return true
}
var obe=objetofeo("","","")
@Composable
fun datos(modifier: Modifier,nombre:String,edad: String,correo: String,botoncito:()-> Unit){
    Column(modifier=modifier.padding(16.dp)) {
        Text("Se ha registrado correctamente ", color = Color.Black)
        Spacer(modifier= Modifier.padding(top = 20.dp))
        TextField(value = nombre,{}, enabled = false, label = {Text("Nombre registrado")})
        Spacer(modifier= Modifier.padding(top = 20.dp))
        TextField(value = edad,{}, enabled = false, label = {Text("Edad registrado")})
        Spacer(modifier= Modifier.padding(top = 20.dp))
        TextField(value = correo,{}, enabled = false, label = {Text("Correo registrado")})
        Spacer(modifier= Modifier.padding(top = 20.dp))
        OutlinedButton(onClick = botoncito ) {Text("Nueva inscripcion") }
    }
}
@Composable
fun inicioScreen(modifier: Modifier= Modifier,bto:()-> Unit){
    var identificarGmail by remember { mutableStateOf(false) }
    var identificarEdad by remember { mutableStateOf(false) }
    var identificarNombre by remember { mutableStateOf(false) }
    var identificarMarca by remember { mutableStateOf(true) }
    var valor by remember { mutableStateOf("") }
    var valor2 by remember { mutableStateOf("") }
    var valor3 by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp)) {
        Text("Inscripción a talleres", color = Color.Black, fontSize = 22.sp)
        Spacer(modifier=Modifier.padding(top = 30.dp))
        Row{
            Text("Casa del Lago - Cupo limitado", color = Color.Black, fontSize = 14.sp)
        }
        OutlinedTextField(value = valor,{valor=it}, label = {Text("Nombre completo")}, isError = identificarNombre , supportingText = {if (identificarNombre){Text("ingresa un nombre valido")} })
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = valor2,{valor2=it}, label = {Text("Correo")}, isError = identificarGmail , supportingText = {if (identificarGmail){Text("ingresa un correo valido")} })
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = valor3,{valor3=it}, label = {Text("Edad")}, isError = identificarEdad , supportingText = {if (identificarEdad){Text("ingresa una edad valida")} })
        SelectorTaller(listOf("Fotografia","Dibujo",))
        var marcado by rememberSaveable { mutableStateOf(false)}
            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked = marcado,
                    onCheckedChange = { marcado = it })
                Text("Acepto el aviso de privacidad", color = Color.Black)
            }
        if (!identificarMarca) {
            Text(
                text = "Debes aceptar los términos",
                color = Color.Red
            )
        }
        OutlinedButton(onClick = {identificarMarca=marcado;identificarGmail=verificarArroba(valor2);identificarEdad=verificarEdad(valor3);identificarNombre=verificarNombre(valor);
            val xd= !identificarGmail && !identificarNombre && !identificarEdad && marcado; if(xd){obe=objetofeo(valor,valor2,valor3);bto()}
        },modifier= Modifier.fillMaxWidth().height(50.dp)) {Text("Inscribirme",color = Color.Black) }
    }
}
@Composable
@Preview
fun pr(){
}