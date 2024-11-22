package remote.lunar.remotedrive.ui.screens.drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

//import remote.lunar.remotedrive.data.remote.fetchBackupFiles
//import remote.lunar.remotedrive.ui.components.BackupItemView

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BackupScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backups") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Lógica de busca */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        }
    ) { paddingValues -> // Recebendo os paddingValues do Scaffold
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                //fileList = fetchBackupFiles()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Aplicando padding para evitar sobreposição com a TopAppBar
        ) {
            Text(
                text = "Não há backups",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}