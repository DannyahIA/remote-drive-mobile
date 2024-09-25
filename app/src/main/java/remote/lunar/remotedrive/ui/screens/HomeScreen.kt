import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Usamos Box para posicionamento
    Box(
        modifier = Modifier
            .fillMaxSize() // Preencher toda a tela
    ) {
        // Texto centralizado
        Text(
            text = "Principal",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center) // Alinhado ao centro da tela
        )
    }
}
