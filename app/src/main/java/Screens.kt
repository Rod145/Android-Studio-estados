import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class Gasto(val concepto: String, val monto: Int, val categoria: String)

fun verificarMonto(texto: String): Boolean {
    if (texto.isBlank() || texto.length > 7) {
        return true
    }
    for (letra in texto) {
        if (!letra.isDigit()) {
            return true
        }
    }
    return texto.toInt() == 0
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioGasto(
    modifier: Modifier = Modifier,
    concepto: String,
    monto: String,
    categoria: String,
    expandido: Boolean,
    onConcepto: (String) -> Unit,
    onMonto: (String) -> Unit,
    onCategoria: (String) -> Unit,
    onExpandido: (Boolean) -> Unit,
    onAgregar: (Gasto) -> Unit
) {
    val opciones = listOf("Comida", "Transporte", "Renta", "Ocio", "Otros")
    val invalido = concepto.isBlank() || verificarMonto(monto)
    Column(modifier = modifier) {
        Text("Nuevo gasto", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 5.dp))
        OutlinedTextField(value = concepto, onValueChange = { onConcepto(it) }, label = { Text("Concepto") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = monto, onValueChange = { onMonto(it) }, label = { Text("Monto") }, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(10.dp))
            ExposedDropdownMenuBox(expanded = expandido, onExpandedChange = { onExpandido(it) }, modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandido) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expandido, onDismissRequest = { onExpandido(false) }) {
                    opciones.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = { onCategoria(it); onExpandido(false) })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Button(
            onClick = {
                onAgregar(Gasto(concepto, monto.toInt(), categoria))
                onConcepto("")
                onMonto("")
            },
            enabled = !invalido,
            colors = ButtonColors(Color(0xFFE8DEF8), Color.Black, Color(0xFFEDEDED), Color.Gray),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) { Text("Agregar gasto", fontWeight = FontWeight.SemiBold) }
    }
}

@Composable
fun filaGasto(modifier: Modifier = Modifier, gasto: Gasto, onclick1: () -> Unit) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(gasto.concepto, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(gasto.categoria, color = Color(0xFF595353), fontSize = 12.sp)
            }
            Text("-\$${gasto.monto}", color = Color(0xFFB2261E), fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.padding(start = 10.dp))
            OutlinedButton(onClick = onclick1, shape = CircleShape, contentPadding = PaddingValues.Zero, modifier = Modifier.size(30.dp), border = null) { Text("X", color = Color.Gray) }
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun tarjetaResumen(modifier: Modifier = Modifier, ingreso: Int, gastado: Int, porcentaje: Int, mayor: String, mayorMonto: Int) {
    val fondoColor = when {
        porcentaje > 100 -> Color(0xFFEEC3C1)
        porcentaje >= 80 -> Color(0xFFFFF6DA)
        else -> Color(0xFFE6F3E7)
    }
    val contenidoColor = when {
        porcentaje > 100 -> Color(0xFFB2261E)
        porcentaje >= 80 -> Color(0xFFF7A725)
        else -> Color(0xFF015903)
    }
    Card(modifier = modifier, shape = RoundedCornerShape(25.dp), colors = CardColors(fondoColor, Color.Black, Color.Unspecified, Color.Unspecified)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Gastado", color = Color(0xFF595353), fontSize = 12.sp)
                    Text("\$$gastado", color = Color.Black, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Saldo", color = Color(0xFF595353), fontSize = 12.sp)
                    Text("\$${ingreso - gastado}", color = contenidoColor, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            LinearProgressIndicator(
                progress = { if (porcentaje > 100) 1f else porcentaje / 100f },
                color = contenidoColor,
                drawStopIndicator = {},
                modifier = Modifier.fillMaxWidth().height(13.dp).clip(shape = RoundedCornerShape(6.dp)),
                gapSize = 0.dp,
                strokeCap = StrokeCap.Butt
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            if (mayorMonto > 0) {
                Text("$porcentaje % usado · Mayor gasto: $mayor (\$$mayorMonto)", color = Color(0xFF595353), fontSize = 12.sp)
            } else {
                Text("$porcentaje % usado", color = Color(0xFF595353), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel: Rviewmodel = viewModel()) {
    val state = viewmodel.uiState.collectAsState().value
    val ingreso = if (verificarMonto(state.ingresoTexto)) 0 else state.ingresoTexto.toInt()
    var gastado = 0
    state.gastos.forEach { gastado += it.monto }
    val porcentaje = if (ingreso == 0) 0 else gastado * 100 / ingreso
    var mayor = ""
    var mayorMonto = 0
    listOf("Comida", "Transporte", "Renta", "Ocio", "Otros").forEach { categoria ->
        var suma = 0
        state.gastos.forEach { if (it.categoria == categoria) { suma += it.monto } }
        if (suma > mayorMonto) {
            mayorMonto = suma
            mayor = categoria
        }
    }
    Column(modifier = modifier.padding(16.dp)) {
        Text("Mi presupuesto", color = Color.Black, fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        OutlinedTextField(
            value = state.ingresoTexto,
            onValueChange = { viewmodel.ingresoTexto(it) },
            label = { Text("Ingreso mensual") },
            isError = verificarMonto(state.ingresoTexto),
            supportingText = { if (verificarMonto(state.ingresoTexto)) { Text("ingresa un monto valido") } },
            modifier = Modifier.fillMaxWidth()
        )
        tarjetaResumen(modifier = Modifier.fillMaxWidth(), ingreso = ingreso, gastado = gastado, porcentaje = porcentaje, mayor = mayor, mayorMonto = mayorMonto)
        if (porcentaje >= 80) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = CardColors(if (porcentaje > 100) Color(0xFFF7DCDA) else Color(0xFFFFDDB0), Color.Black, Color.Unspecified, Color.Unspecified)
            ) {
                if (porcentaje > 100) {
                    Text("Te pasaste por \$${gastado - ingreso}", color = Color(0xFFB2261E), fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(12.dp))
                } else {
                    Text("Cuidado: pasaste el 80 %", color = Color(0xFF5D4000), fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(12.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        formularioGasto(
            modifier = Modifier.fillMaxWidth(),
            concepto = state.concepto,
            monto = state.monto,
            categoria = state.categoria,
            expandido = state.expandido,
            onConcepto = { viewmodel.concepto(it) },
            onMonto = { viewmodel.monto(it) },
            onCategoria = { viewmodel.categoria(it) },
            onExpandido = { viewmodel.expandido(it) },
            onAgregar = { viewmodel.gastos(state.gastos + it) }
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text("Gastos recientes", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 5.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.gastos) { gasto ->
                filaGasto(gasto = gasto) { viewmodel.gastos(state.gastos.filter { it !== gasto }) }
            }
        }
    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}