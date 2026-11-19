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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorTaller(viewmodel:Rviewmodel= viewModel()) {
    val state=viewmodel.uiState.collectAsState().value
    ExposedDropdownMenuBox(expanded = state.expandido, onExpandedChange = {viewmodel.expandido(it)}) {
        OutlinedTextField(
            value = state.seleccion,
            onValueChange = {},
            readOnly = true,
            label = { Text("Taller") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(state.expandido) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = state.expandido, onDismissRequest = { viewmodel.expandido(false)}) {
            state.opciones.forEach {
                DropdownMenuItem(text = { Text(it) }, onClick = { viewmodel.seleccion(it); viewmodel.expandido(false)})
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
fun inicioScreen(modifier: Modifier= Modifier,bto:()-> Unit, viewmodel: Rviewmodel=viewModel()){
    val state=viewmodel.uiState.collectAsState().value
    Column(modifier = modifier.padding(16.dp)) {
        Text("Inscripción a talleres", color = Color.Black, fontSize = 22.sp)
        Spacer(modifier=Modifier.padding(top = 30.dp))
        Row{
            Text("Casa del Lago - Cupo limitado", color = Color.Black, fontSize = 14.sp)
        }
        OutlinedTextField(value = state.valor,{viewmodel.valor(it)}, label = {Text("Nombre completo")}, isError = state.identificarNombre , supportingText = {if (state.identificarNombre){Text("ingresa un nombre valido")} })
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = state.valor2,{viewmodel.valor2(it)}, label = {Text("Correo")}, isError = state.identificarGmail , supportingText = {if (state.identificarGmail){Text("ingresa un correo valido")} })
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = state.valor3,{viewmodel.valor3(it)}, label = {Text("Edad")}, isError = state.identificarEdad , supportingText = {if (state.identificarEdad){Text("ingresa una edad valida")} })
        SelectorTaller()
            Row(verticalAlignment = Alignment.CenterVertically){
                Checkbox(
                    checked = state.marcado,
                    onCheckedChange = { viewmodel.marcado(it) })
                Text("Acepto el aviso de privacidad", color = Color.Black)
            }
        if (!state.identificarMarca) {
            Text(
                text = "Debes aceptar los términos",
                color = Color.Red
            )
        }
        OutlinedButton(onClick = {viewmodel.identificarMarca(state.marcado);viewmodel.identificarGmail(verificarArroba(state.valor2));viewmodel.identificarEdad(verificarEdad(state.valor3));viewmodel.identificarNombre(verificarNombre(state.valor));
            val xd= !state.identificarGmail && !state.identificarNombre && !state.identificarEdad && state.marcado; if(xd){obe=objetofeo(state.valor,state.valor2,state.valor3);bto()}
        },modifier= Modifier.fillMaxWidth().height(50.dp)) {Text("Inscribirme",color = Color.Black) }
    }
}
@Composable
@Preview
fun pr(){
    var pantalla by remember { mutableStateOf(1) }
    if (pantalla==1){
        inicioScreen(
            modifier = Modifier.fillMaxSize().background(Color.White),{pantalla=2}
        )}else{datos(modifier = Modifier.fillMaxSize().background(Color.White),obe.nombre,obe.edad,obe.correo,{pantalla=1})}

}