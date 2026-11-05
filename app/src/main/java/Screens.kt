import android.widget.RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import java.util.Locale

@Composable
fun RatingBar(modifier: Modifier = Modifier,textodesc:String, clicko: (Int) -> Unit) {

    var rating by remember { mutableIntStateOf(0) }
    Column(modifier = modifier) {
        Text(textodesc, color = Color(0xFF5E5E5E), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(top=10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            for (i in 1..5) {
                Icon(
                    imageVector = if (i <= rating)
                        Icons.Default.Star
                    else
                        Icons.Default.StarBorder,

                    contentDescription = null,

                    tint = Color.Yellow,

                    modifier = Modifier.size(40.dp).clickable {
                        rating = i
                        clicko(rating)
                    }
                )
            }
            Spacer(modifier=Modifier.weight(1f))
            when(rating){
                1->Text("Malo", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                2->Text("Regular", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                3->Text("Bueno", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                4->Text("Muy bueno", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                5->Text("Excelente", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                else-> Text("", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}

@Composable
fun inicioScreen(modifier: Modifier = Modifier) {
    var caltotal by remember { mutableDoubleStateOf(0.0) }
    Column(modifier = modifier.padding(16.dp)) {
        RatingBar(textodesc = "Comida") {calificacion->caltotal+= calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top=10.dp))
        RatingBar(textodesc = "Servicio") {calificacion->caltotal+= calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top=10.dp))
        RatingBar(textodesc = "Ambiente") {calificacion->caltotal+= calificacion.toDouble() }
        Spacer(modifier = Modifier.padding(top=10.dp))
        Card(modifier=Modifier.fillMaxWidth()) { Row(modifier=modifier.padding(16.dp)) {Text("Calificacion general", color = Color(0xFF5E5E5E), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("${String.format(Locale, format = "%.,2")caltotal/3}", color = Color(0xFF181818), fontSize = 18.sp, fontWeight = FontWeight.Bold)} }
    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}