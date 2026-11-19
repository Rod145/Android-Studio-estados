import android.R
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

@Composable
fun inicioScreen(modifier: Modifier = Modifier,viewmodel:Rviewmodel= viewModel()){
    Column(modifier = modifier) {
        val state=viewmodel.uiState.collectAsState().value
        OutlinedTextField(value = state.text1, onValueChange = {viewmodel.text1(it)}, keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Number ), shape = RoundedCornerShape(20.dp), modifier = Modifier.width(100.dp).height(
                55.dp))
        Spacer(modifier =  Modifier.padding(10.dp))
        OutlinedTextField(value = state.text2, onValueChange = {viewmodel.text2(it)}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), shape = RoundedCornerShape(20.dp), modifier = Modifier.width(100.dp).height(
            55.dp))
        Spacer(modifier =  Modifier.padding(10.dp))
        Button(modifier = Modifier.width(100.dp).height(50.dp),onClick = {
            viewmodel.resultado()
        }) {
            Text("Sumar")
        }
        Spacer(modifier =  Modifier.padding(10.dp))
        OutlinedTextField(value = state.resultado.toString(), onValueChange = {}, readOnly = true, shape = RoundedCornerShape(20.dp), modifier = Modifier.width(100.dp).height(55.dp))
    }
}
@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}