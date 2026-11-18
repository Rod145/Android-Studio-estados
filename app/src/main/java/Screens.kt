import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun botonpechocho(
    modifier: Modifier = Modifier,
    texto: String,
    precio: String,
    cantidad: Int,
    clico: () -> Unit,
    clico2: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(texto, color = Color.Black, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text("$$precio c/u", color = Color.Gray, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = clico,
            shape = CircleShape,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp)
        ) { Text("-", color = Color.Black) }
        Text(
            "$cantidad",
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
        OutlinedButton(
            onClick = clico2,
            shape = CircleShape,
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp)
        ) { Text("+", color = Color.Black) }
    }
    Spacer(modifier = Modifier.padding(top = 10.dp))
    HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
}

@SuppressLint("DefaultLocale")
@Composable
fun cartaVenta(modifier: Modifier = Modifier, articulos: Int, subtotal: Double) {
    var descuento = when {
        subtotal >= 200 -> subtotal * 0.10
        else -> 0.0
    }
    var envio = when {
        subtotal >= 150.0 -> 0.0
        subtotal == 0.0 -> 0.0
        else -> 25.00
    }
    Card(
        modifier = modifier, colors = CardColors(
            Color(0xFFF1EBF5), Color.Unspecified, Color.Unspecified,
            Color.Unspecified
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Articulos", color = Color.Gray, fontSize = 18.sp)
                Text("$articulos", color = Color.Black, fontSize = 18.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal", color = Color.Gray, fontSize = 18.sp)
                Text("$$subtotal", color = Color.Black, fontSize = 18.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Descuento 10%", color = Color.Gray, fontSize = 18.sp)
                Text(
                    "-$" + String.format("%.2f", descuento),
                    color = Color(0xFF43B94F),
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Envio", color = Color.Gray, fontSize = 18.sp)
                Text(
                    if (envio == 0.0) {
                        "GRATIS"
                    } else {
                        "$25.00"
                    }, color = Color(0xFF43B94F), fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("TOTAL", color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(
                    "$" + (subtotal - descuento + envio).toString(),
                    color = Color(0xFF664FA3),
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Composable
fun inicioScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Tienda Don Pepe", color = Color.Black, fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(top = 40.dp))
        var total by remember { mutableDoubleStateOf(0.0) }
        val tortillas = 24.00
        val leche = 27.50
        val huevo = 52.00
        val frijol = 38.00
        var cantidad1 by remember { mutableIntStateOf(0) }
        var cantidad2 by remember { mutableIntStateOf(0) }
        var cantidad3 by remember { mutableIntStateOf(0) }
        var cantidad4 by remember { mutableIntStateOf(0) }
        botonpechocho(
            texto = "Tortillas 1kg",
            precio = "$tortillas",
            cantidad = cantidad1,
            clico = {
                if (cantidad1 != 0) {
                    total -= tortillas; cantidad1--
                }
            },
            clico2 = { total += tortillas; cantidad1++ })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        botonpechocho(
            texto = "Leche 1L",
            precio = "$leche",
            cantidad = cantidad2,
            clico = {
                if (cantidad2 != 0) {
                    total -= leche; cantidad2--
                }
            },
            clico2 = { total += leche; cantidad2++ })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        botonpechocho(
            texto = "Huevo 12 pz",
            precio = "$huevo",
            cantidad = cantidad3,
            clico = {
                if (cantidad3 != 0) {
                    total -= huevo; cantidad3--
                }
            },
            clico2 = { total += huevo; cantidad3++ })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        botonpechocho(
            texto = "Frijol 1 kg",
            precio = "$frijol",
            cantidad = cantidad4,
            clico = {
                if (cantidad4 != 0) {
                    total -= frijol; cantidad4--
                }
            },
            clico2 = { total += frijol; cantidad4++ })
        Spacer(modifier = Modifier.padding(top = 10.dp))
        cartaVenta(
            modifier = Modifier.height(220.dp),
            articulos = cantidad1 + cantidad2 + cantidad3 + cantidad4,
            subtotal = total
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(
                onClick = {
                    cantidad1 = 0; cantidad2 = 0; cantidad3 = 0; cantidad4 = 0; total = 0.0
                }, colors = ButtonColors(
                    Color.Transparent,
                    Color.Unspecified, Color.Unspecified, Color.Unspecified
                ), modifier = Modifier.height(50.dp)
            ) {
                Text(
                    "Vaciar", color = Color(
                        0xFF664FA3
                    )
                )
            }
            OutlinedButton(
                onClick = {}, colors = ButtonColors(
                    Color(0xFF664FA3), Color.Unspecified,
                    Color.Unspecified, Color.Unspecified
                ), modifier = Modifier
                    .width(240.dp)
                    .height(50.dp)
            ) { Text("Confirmar pedido", color = Color(0xF2F6F6F6)) }
        }
    }
}

@Composable
@Preview
fun Pr() {
    inicioScreen(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}