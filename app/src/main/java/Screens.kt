import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Pantalla { Inicio, Pregunta, Resultados }

class Pregunta(val enunciado: String, val opciones: List<String>, val correcta: Int)

fun verificarNombre(texto: String): Boolean {
    if (texto.isBlank() || texto.trim().length < 2) {
        return true
    }
    return false
}

@Composable
fun pantallaInicio(modifier: Modifier = Modifier, nombre: String, onNombre: (String) -> Unit, onComenzar: () -> Unit) {
    Column(modifier = modifier) {
        Text("Quiz de programación", color = Color.Black, fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { onNombre(it) },
            label = { Text("Nombre") },
            isError = nombre.isNotEmpty() && verificarNombre(nombre),
            supportingText = { if (nombre.isNotEmpty() && verificarNombre(nombre)) { Text("ingresa un nombre valido") } },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(
            onClick = onComenzar,
            enabled = !verificarNombre(nombre),
            colors = ButtonColors(Color(0xFF664FA3), Color.White, Color(0xFFEDEDED), Color.Gray),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Comenzar", fontWeight = FontWeight.SemiBold) }
    }
}

@Composable
fun pantallaPregunta(
    modifier: Modifier = Modifier,
    pregunta: Pregunta,
    numero: Int,
    puntaje: Int,
    seleccion: Int,
    confirmado: Boolean,
    onSeleccion: (Int) -> Unit,
    onConfirmar: () -> Unit,
    onSiguiente: () -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Pregunta $numero de 5", color = Color(0xFF595353), fontSize = 12.sp, modifier = Modifier.weight(1f))
            Text("Puntaje: $puntaje", color = Color(0xFF664FA3), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.padding(top = 5.dp))
        LinearProgressIndicator(
            progress = { numero / 5f },
            color = Color(0xFF664FA3),
            drawStopIndicator = {},
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(shape = RoundedCornerShape(6.dp)),
            gapSize = 0.dp,
            strokeCap = StrokeCap.Butt
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(pregunta.enunciado, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 20.dp))
        pregunta.opciones.forEachIndexed { i, opcion ->
            var fondo = Color(0xFFFFFBFE)
            var borde = Color(0xFFCAC4D0)
            var marca = ""
            if (confirmado && i == pregunta.correcta) {
                fondo = Color(0xFFDBEDD7)
                borde = Color(0xFF2E7D32)
                marca = "✓"
            } else if (confirmado && i == seleccion) {
                fondo = Color(0xFFF7DCDA)
                borde = Color(0xFFB2261E)
                marca = "✕"
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardColors(fondo, Color.Black, Color.Unspecified, Color.Unspecified),
                border = BorderStroke(1.dp, borde)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().clickable(enabled = !confirmado) { onSeleccion(i) }.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = seleccion == i, onClick = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(opcion, color = Color.Black, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                    Text(marca, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
        if (confirmado) {
            if (seleccion == pregunta.correcta) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardColors(Color(0xFFDBEDD7), Color.Black, Color.Unspecified, Color.Unspecified)
                ) {
                    Text("¡Correcto!", color = Color(0xFF2E7D32), fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(12.dp))
                }
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardColors(Color(0xFFF7DCDA), Color.Black, Color.Unspecified, Color.Unspecified)
                ) {
                    Text(
                        "Incorrecto. La respuesta correcta es ${pregunta.opciones[pregunta.correcta]}.",
                        color = Color(0xFFB2261E),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onConfirmar,
                enabled = seleccion != -1 && !confirmado,
                colors = ButtonColors(Color(0xFF664FA3), Color.White, Color(0xFFEDEDED), Color.Gray),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.weight(1f).height(50.dp)
            ) { Text("Confirmar", fontWeight = FontWeight.SemiBold) }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onSiguiente,
                enabled = confirmado,
                colors = ButtonColors(Color(0xFF664FA3), Color.White, Color(0xFFEDEDED), Color.Gray),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.weight(1f).height(50.dp)
            ) { Text(if (numero == 5) "Ver resultados" else "Siguiente", fontWeight = FontWeight.SemiBold) }
        }
    }
}

@Composable
fun pantallaResultados(modifier: Modifier = Modifier, nombre: String, puntaje: Int, onReintentar: () -> Unit, onCambiar: () -> Unit) {
    val mensaje = when {
        puntaje <= 2 -> "Sigue practicando"
        puntaje <= 4 -> "Buen trabajo"
        else -> "Perfecto"
    }
    Column(modifier = modifier) {
        Text("¡Muy bien, $nombre! $puntaje de 5", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(mensaje, color = Color(0xFF664FA3), fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onReintentar,
            colors = ButtonColors(Color(0xFF664FA3), Color.White, Color(0xFFEDEDED), Color.Gray),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Reintentar", fontWeight = FontWeight.SemiBold) }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = onCambiar,
            colors = ButtonColors(Color(0xFFE8DEF8), Color.Black, Color(0xFFEDEDED), Color.Gray),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Cambiar jugador", fontWeight = FontWeight.SemiBold) }
    }
}

@Composable
fun inicioScreen(modifier: Modifier = Modifier) {
    val preguntas = listOf(
        Pregunta("¿Qué tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
        Pregunta("¿Qué estructura repite código mientras se cumpla una condición?", listOf("if", "while", "when", "class"), 1),
        Pregunta("¿Qué palabra declara una variable que no puede cambiar?", listOf("var", "val", "fun", "let"), 1),
        Pregunta("¿Cuál es el resultado de 7 % 3?", listOf("1", "2", "3", "0"), 0),
        Pregunta("¿Qué índice tiene el primer elemento de una lista?", listOf("1", "0", "-1", "2"), 1)
    )
    var pantalla by remember { mutableStateOf(Pantalla.Inicio) }
    var nombre by remember { mutableStateOf("") }
    var indice by rememberSaveable { mutableIntStateOf(0) }
    var puntaje by rememberSaveable { mutableIntStateOf(0) }
    var seleccion by rememberSaveable { mutableIntStateOf(-1) }
    var confirmado by remember { mutableStateOf(false) }
    when (pantalla) {
        Pantalla.Inicio -> pantallaInicio(
            modifier = modifier.padding(16.dp),
            nombre = nombre,
            onNombre = { nombre = it },
            onComenzar = { pantalla = Pantalla.Pregunta }
        )
        Pantalla.Pregunta -> pantallaPregunta(
            modifier = modifier.padding(16.dp),
            pregunta = preguntas[indice],
            numero = indice + 1,
            puntaje = puntaje,
            seleccion = seleccion,
            confirmado = confirmado,
            onSeleccion = { seleccion = it },
            onConfirmar = {
                confirmado = true
                if (seleccion == preguntas[indice].correcta) {
                    puntaje += 1
                }
            },
            onSiguiente = {
                if (indice == 4) {
                    pantalla = Pantalla.Resultados
                } else {
                    indice += 1
                    seleccion = -1
                    confirmado = false
                }
            }
        )
        Pantalla.Resultados -> pantallaResultados(
            modifier = modifier.padding(16.dp),
            nombre = nombre.trim(),
            puntaje = puntaje,
            onReintentar = {
                indice = 0
                puntaje = 0
                seleccion = -1
                confirmado = false
                pantalla = Pantalla.Pregunta
            },
            onCambiar = {
                nombre = ""
                indice = 0
                puntaje = 0
                seleccion = -1
                confirmado = false
                pantalla = Pantalla.Inicio
            }
        )
    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}