import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.mutableListOf
@Composable
fun ola(modifier: Modifier= Modifier,listanombres: List<String>){
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(listanombres.size){i->
            Card {
                Text(
                    text = listanombres.get(i),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun inicioScreen(modifier: Modifier = Modifier, numero: Int, miclick:()->Unit){
    Column(modifier = modifier) {
        Text(text= "$numero", fontSize =80.sp )
        Button(onClick = miclick) {
            Text("aumentar")
        }

    }
}
@Composable
@Preview
fun pr(){
}