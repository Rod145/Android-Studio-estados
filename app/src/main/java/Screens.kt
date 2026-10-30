import android.R
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.w3c.dom.Text

@Composable
fun TarjetaVisitasDiarias(modifier: Modifier= Modifier,visitas: Int){
    Card(modifier=modifier, shape = RoundedCornerShape(25.dp), colors = CardColors(Color(0xFFEAEAEE),Color.Black,Color.Unspecified,Color.Unspecified)) {
        ConstraintLayout(modifier= Modifier.fillMaxSize()) {
            val b1=createRef()
            Box(modifier=Modifier.constrainAs(b1){
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width= Dimension.fillToConstraints
                height= Dimension.fillToConstraints
            }) {
                Text("Visitas del dia", color = Color(0xFF595353), fontSize = 17.sp)
                Text("$visitas", modifier=Modifier.width(340.dp).offset(y = 5.dp), textAlign = TextAlign.End, fontSize = 40.sp)
            }
        }
    }
}
@Composable
fun Tarjetadecambios(modifier: Modifier= Modifier,personas:Int){
    var porcentajeP=(personas*100)/50
    var fondoColor=when{
        porcentajeP in 70..<100 -> Color(0xFFEEDCC1)
        porcentajeP ==100-> Color(0xFFEEC3C1)
        else -> Color(0xFFE6F3E7)
    }
    var contenidoColor= when{
        porcentajeP in 70..<100-> Color(0xFFF7A725)
        porcentajeP== 100->Color(0xFFB2261E)
        else-> Color(0xFF015903)
    }
    Card(modifier=modifier, shape = RoundedCornerShape(25.dp), colors = CardColors(fondoColor,contenidoColor,Color.Black,Color.Black)) {
       ConstraintLayout(modifier= Modifier.fillMaxSize()) {
           val b1=createRef()
           Box(modifier=Modifier.constrainAs(b1){
               top.linkTo(parent.top, margin = 16.dp)
               bottom.linkTo(parent.bottom, margin = 16.dp)
               start.linkTo(parent.start, margin = 16.dp)
               end.linkTo(parent.end, margin = 16.dp)
               width= Dimension.fillToConstraints
               height= Dimension.fillToConstraints
           }) {
               Column{
                   Text("$personas/50", textAlign = TextAlign.End,modifier=Modifier.width(250.dp), fontSize = 70.sp, fontWeight = FontWeight.Bold)
                   Text("personas en la sala", textAlign = TextAlign.End, fontWeight = FontWeight.SemiBold,color=Color(0xB7020202),modifier = Modifier.width(230.dp))
                   Spacer(Modifier.padding(10.dp))
                   LinearProgressIndicator(progress = {personas/50f}, color = contenidoColor, drawStopIndicator = {},modifier= Modifier.fillMaxWidth().height(13.dp).clip(shape = RoundedCornerShape(6.dp)), gapSize = 0.dp, strokeCap = StrokeCap.Butt)
                   Spacer(Modifier.padding(10.dp))
                   Text("$porcentajeP% de ocupacion", fontSize = 17.sp,color = contenidoColor, textAlign = TextAlign.End, fontWeight = FontWeight.SemiBold,modifier = Modifier.width(230.dp))
                   Spacer(Modifier.padding(5.dp))
                   Text("Aforo disponible", textAlign = TextAlign.End, fontSize = 10.sp, fontWeight = FontWeight.SemiBold,color=Color(0xB7020202),modifier = Modifier.width(200.dp))

               }

           }
       }
    }
}
@Composable
fun inicioScreen(modifier: Modifier = Modifier)
{var personas by rememberSaveable { mutableIntStateOf(0) }
    var visitasdaily by rememberSaveable { mutableIntStateOf(0) }
        ConstraintLayout(modifier=modifier) {
            val b1=createRef()
            Box(modifier=Modifier.constrainAs(b1){
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                height= Dimension.fillToConstraints
                width= Dimension.fillToConstraints
            }) {
                Column{
                    Text("Biblioteca Central", fontSize = 24.sp, color= Color.Black)
                    Spacer(modifier=Modifier.padding(13.dp))
                    Text("Sala de estudio | capacidad 50",color= Color(0xFF424344))
                    Tarjetadecambios(modifier = Modifier.fillMaxWidth().height(300.dp).padding(vertical = 20.dp), personas = personas)
                    Row{OutlinedButton(onClick = {if (personas==0){return@OutlinedButton}; personas-=1}, colors = ButtonColors(Color.Transparent, Color.Black, Color.Unspecified,Color.Unspecified), modifier = Modifier.height(55.dp).width(150.dp), border = BorderStroke(width = 1.dp, color = Color(
                        0xFF654EA2
                    )
                    )) {Text("- Sale") }
                        OutlinedButton(onClick = {if(personas==50){return@OutlinedButton}; personas+=1; visitasdaily+=1}, colors = ButtonColors(Color(0xFF664FA3),Color.Black,Color.Unspecified,Color.Unspecified), modifier = Modifier.height(55.dp).width(150.dp).offset(50.dp), border = null) {Text("+ Entra", color = Color.White) }}
                    TarjetaVisitasDiarias(visitas=visitasdaily, modifier = Modifier.fillMaxWidth().height(90.dp).offset(y = 25.dp))

                }

            }

    }
}
@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}