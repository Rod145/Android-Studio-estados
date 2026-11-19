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
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale
import kotlin.math.ceil

@Composable
fun tarjetaprop(
    modifier: Modifier = Modifier,
    select1: Boolean,
    select2: Boolean,
    select3: Boolean,
    select4: Boolean,
    valorS: Float,
    valor: String,
    cantidad: Int,
    redondeo: Boolean
) {
    var propina = when {
        select1 -> 10.00
        select2 -> 15.00
        select3 -> 20.00
        select4 -> valorS.toDouble()
        else -> 0.00
    }
    var porcn = String.format(Locale.US, "%.2f", valor.toDouble() * propina / 100).toDouble()
    var total = porcn + valor.toDouble()
    var totalR = String.format(Locale.US, "%.2f", total / cantidad).toDouble()
    var redondeorest = when {
        redondeo -> String.format(Locale.US, "%.2f", totalR - totalR.toInt()).toDouble()
        else -> 0.0
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
                Text("Propina (${propina.toInt()}%)", color = Color.Gray)
                Text("$$porcn", color = Color.Black)
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total con propina", color = Color.Gray)
                Text("$$total", color = Color.Black)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (cantidad != 1) {
                    Text("Cada quien paga", color = Color.Gray)
                } else {
                    Text("Pagas", color = Color.Gray)
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    "$${
                        if (redondeo) {
                            ceil(totalR)
                        } else {
                            totalR
                        }
                    }", color = Color(0xFF664FA3), fontSize = 50.sp
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                if (cantidad != 1) {
                    Text("entre $cantidad personas", color = Color.Gray)
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
                Text("Sobrante por redondeo: $$redondeorest", color = Color.Gray)
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel:Rviewmodel= viewModel()) {
    val state=viewmodel.uiState.collectAsState().value
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            "Cuenta Dividida",
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        OutlinedTextField(
            value = state.valor,
            onValueChange = { viewmodel.valor(it) },
            isError = !state.valido,
            supportingText = {
                if (!state.valido) {
                    Text("Ingresa un numero")
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                Color.Black,
                Color.Unspecified
            ),
            label = {
                Text(
                    "Monto de la cuenta",
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text("Propina", color = Color.Black)
        Row {
            FilterChip(
                selected = state.seleccion1,
                onClick = {
                    viewmodel.seleccion1(true); viewmodel.seleccion2(false); viewmodel.seleccion3(false); viewmodel.seleccion4(false);
                },
                label = { Text("10%", color = Color.Black) })
            FilterChip(
                selected = state.seleccion2,
                onClick = {
                    viewmodel.seleccion2(true); viewmodel.seleccion1(false); viewmodel.seleccion3(false); viewmodel.seleccion4(false);
                },
                label = { Text("15%", color = Color.Black) },
                modifier = Modifier.padding(start = 5.dp)
            )
            FilterChip(
                selected = state.seleccion3,
                onClick = {
                    viewmodel.seleccion3(true); viewmodel.seleccion1(false); viewmodel.seleccion1(false); viewmodel.seleccion4(false);
                },
                label = { Text("20%", color = Color.Black) },
                modifier = Modifier.padding(start = 5.dp)
            )
            FilterChip(
                selected = state.seleccion4,
                onClick = {
                    viewmodel.seleccion4(true); viewmodel.seleccion1(false); viewmodel.seleccion2(false); viewmodel.seleccion3(false);
                },
                label = { Text("Otro", color = Color.Black) },
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        if (state.seleccion4) {
            Text("${state.valorS.toInt()}%", color = Color.Black, textAlign = TextAlign.End)
            Slider(value = state.valorS, onValueChange = { viewmodel.valorS(it) }, valueRange = 0f..30f)
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("personas", color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = {
                    if (state.personas != 1) {
                        viewmodel.personas(state.personas-1)
                    }
                },
                shape = CircleShape,
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(0.dp)
            ) { Text("-", fontSize = 30.sp, color = Color.Black) }
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Text("${state.personas}", color = Color.Black)
            Spacer(modifier = Modifier.padding(start = 10.dp))
            OutlinedButton(
                onClick = { viewmodel.personas(state.personas+1) },
                shape = CircleShape,
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(0.dp)
            ) { Text("+", fontSize = 30.sp, color = Color.Black) }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Redondear por persona", color = Color.Black)
            Switch(
                checked = state.redondeo,
                onCheckedChange = { viewmodel.redondeo(it)},
                modifier = Modifier.scale(1.2f)
            )
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.padding(top = 10.dp))
        tarjetaprop(
            select1 = state.seleccion1,
            select2 = state.seleccion2,
            select3 = state.seleccion3,
            select4 = state.seleccion4,
            valorS = state.valorS,
            valor = if (state.valido) {
                state.valor
            } else {
                "0.00"
            },
            cantidad = state.personas,
            redondeo = state.redondeo
        )
    }
}

@Composable
@Preview
fun pr() {
    inicioScreen(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}