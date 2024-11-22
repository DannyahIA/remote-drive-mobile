import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import remote.lunar.remotedrive.data.model.FileItem
//import remote.lunar.remotedrive.data.remote.fetchRootFiles
//import remote.lunar.remotedrive.data.remote.fetchStarredFiles

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StarredScreen(navController: NavController) {
    var starredFiles by remember { mutableStateOf<List<FileItem>>(emptyList()) }
    val scope = rememberCoroutineScope()

    Scaffold {
        LaunchedEffect(Unit) {
            scope.launch {
                //starredFiles = fetchStarredFiles()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Nenhum arquivo disponível",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
