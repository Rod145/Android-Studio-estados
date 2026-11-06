import android.annotation.SuppressLint
import android.widget.RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun RatingBar(modifier: Modifier = Modifier, textodesc: String, clicko: (Int) -> Unit) {

    var rating by remember { mutableIntStateOf(0) }
    Column(modifier = modifier) {
        Text(textodesc, color = Color(0xFF5E5E5E), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            for (i in 1..5) {
                Icon(
                    imageVector = if (i <= rating)
                        Icons.Default.Star
                    else
                        Icons.Default.StarBorder,

                    contentDescription = null,

                    tint = Color.Yellow,

                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            rating = i
                            clicko(rating)
                        }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            when (rating) {
                1 -> Text(
                    "Malo",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                2 -> Text(
                    "Regular",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                3 -> Text(
                    "Bueno",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                4 -> Text(
                    "Muy bueno",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                5 -> Text(
                    "Excelente",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                else -> Text(
                    "",
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@SuppressLint("DefaultLocale")
@Composable
fun inicioScreen(modifier: Modifier = Modifier) {
    var cal1 by remember { mutableDoubleStateOf(0.0) }
    var cal2 by remember { mutableDoubleStateOf(0.0) }
    var cal3 by remember { mutableDoubleStateOf(0.0) }
    var valor by remember { mutableStateOf("") }
    var familiar by remember { mutableStateOf(false) }
    var economico by remember { mutableStateOf(false) }
    var rapido by remember { mutableStateOf(false) }
    var romantico by remember { mutableStateOf(false) }
    var vegetariano by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            "Restaurante El Mezquite",
            color = Color(0xFF000000),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(
            "Califica tu experiencia",
            color = Color(0xFF5E5E5E),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        RatingBar(textodesc = "Comida") { calificacion -> cal1 = calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        RatingBar(textodesc = "Servicio") { calificacion -> cal2 = calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        RatingBar(textodesc = "Ambiente") { calificacion -> cal3 = calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color(0xFFE4DEE7))
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Calificacion general",
                    color = Color(0xFF5E5E5E),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    String.format(format = "%.2f", (cal1 + cal2 + cal3) / 3),
                    color = Color(0xFF181818),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                )
            }
        }
        OutlinedTextField(
            value = valor,
            isError = valor.length !in 21..180,
            onValueChange = { if (valor.length <= 200){ valor= it} },
            label = { Text("Comentario", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(
                    "${valor.length}/200", color = if (valor.length !in 21..180) {
                        Color.Red
                    } else {
                        Color.Black
                    }, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()
                )
            })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row {
            FilterChip(
                selected = familiar,
                { familiar = !familiar },
                label = { Text("Familiar", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = economico,
                { economico = !economico },
                label = { Text("Economico", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = rapido,
                { rapido = !rapido },
                label = { Text("Rapido", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
        }
        Row {
            FilterChip(
                selected = romantico,
                { romantico = !romantico },
                label = { Text("Romántico", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = vegetariano,
                { vegetariano = !vegetariano },
                label = { Text("Vegetariano", color = Color.Black) })
        }
        Button(onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text(
                "Publicar",
                color = Color.White
            )
        }

    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}