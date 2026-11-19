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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale

@Composable
fun RatingBar(modifier: Modifier = Modifier, textodesc: String, rating: Int, clicko: (Int) -> Unit) {
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
                            clicko(i)
                        }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            when (rating) {
                1 -> Text("Malo", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                2 -> Text("Regular", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                3 -> Text("Bueno", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                4 -> Text("Muy bueno", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                5 -> Text("Excelente", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                else -> Text("", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel: Rviewmodel = viewModel()) {
    val state = viewmodel.uiState.collectAsState().value

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
        RatingBar(textodesc = "Comida", rating = state.cal1.toInt()) { calificacion -> viewmodel.cal1(calificacion.toDouble()) }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        RatingBar(textodesc = "Servicio", rating = state.cal2.toInt()) { calificacion -> viewmodel.cal2(calificacion.toDouble()) }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        RatingBar(textodesc = "Ambiente", rating = state.cal3.toInt()) { calificacion -> viewmodel.cal3(calificacion.toDouble()) }
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
                    String.format(format = "%.2f", (state.cal1 + state.cal2 + state.cal3) / 3),
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
            value = state.valor,
            isError = state.valor.length !in 21..180,
            onValueChange = { if (state.valor.length <= 200) { viewmodel.valor(it) } },
            label = { Text("Comentario", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(
                    "${state.valor.length}/200", color = if (state.valor.length !in 21..180) {
                        Color.Red
                    } else {
                        Color.Black
                    }, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()
                )
            })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row {
            FilterChip(
                selected = state.familiar,
                { viewmodel.familiar(!state.familiar) },
                label = { Text("Familiar", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = state.economico,
                { viewmodel.economico(!state.economico) },
                label = { Text("Economico", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = state.rapido,
                { viewmodel.rapido(!state.rapido) },
                label = { Text("Rapido", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
        }
        Row {
            FilterChip(
                selected = state.romantico,
                { viewmodel.romantico(!state.romantico) },
                label = { Text("Romántico", color = Color.Black) })
            Spacer(modifier = Modifier.padding(start = 10.dp))
            FilterChip(
                selected = state.vegetariano,
                { viewmodel.vegetariano(!state.vegetariano) },
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

