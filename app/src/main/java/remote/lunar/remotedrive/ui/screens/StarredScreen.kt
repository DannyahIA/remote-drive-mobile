import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StarredScreen(navController: NavController) {
    // Usamos Box para posicionamento
    Box(
        modifier = Modifier
            .fillMaxSize() // Preencher toda a tela
    ) {
        // Texto centralizado
        Text(
            text = "Arquivos com estrela",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center) // Alinhado ao centro da tela
        )
    }
}
