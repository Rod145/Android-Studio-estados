import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun inicioScreen(modifier: Modifier = Modifier, viewmodel:Rviewmodel= viewModel()){
    val state=viewmodel.uistate.collectAsState().value
    Column(modifier = modifier) {
        Text(text= "${state.x}", fontSize =80.sp )
        Button(onClick = {viewmodel.aumentar()}) {
            Text("aumentar")
        }

    }
}
@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}