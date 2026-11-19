import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun inicioScreen(modifier: Modifier = Modifier,viewmodel:Rviewmodel= viewModel()){
    val state=viewmodel.uitState.collectAsState().value
    Column(modifier = modifier.padding(top = 50.dp)) {
        OutlinedTextField(value = state.texto1, onValueChange = {viewmodel.texto1(it)}, shape = RoundedCornerShape(20.dp), modifier = Modifier.width(150.dp).height(50.dp))
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(value = state.texto2, onValueChange = {viewmodel.texto2(it)}, shape = RoundedCornerShape(20.dp), modifier = Modifier.width(150.dp).height(50.dp))
        Spacer(modifier = Modifier.padding(5.dp))
        Row{
            Button(onClick ={
                viewmodel.agregar()}) { Text("Agregar")}
            Button(onClick ={
                viewmodel.eliminarTodo()
            }, modifier = Modifier.offset(10.dp)) { Text("Eliminar todo")}
        }

        Spacer(modifier = Modifier.padding(5.dp))
        LazyColumn(modifier=Modifier.heightIn(max = 100.dp)) {
            items(state.objeto) { tarjeta ->
                mycard(tarjeta,{viewmodel.eliminar(tarjeta)})
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
    }

@Composable
@Preview
fun pr(){
    inicioScreen(modifier = Modifier.fillMaxSize().background(Color.White))
}