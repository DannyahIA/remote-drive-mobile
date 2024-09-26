package remote.lunar.remotedrive.ui.screens.drawer

import FileItemView
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
import remote.lunar.remotedrive.data.model.FileItem
import remote.lunar.remotedrive.data.remote.fetchBackupFiles
import remote.lunar.remotedrive.data.remote.fetchRootFiles

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
    ) {
        var fileList by remember { mutableStateOf<List<FileItem>>(emptyList()) }
        val scope = rememberCoroutineScope()

        Scaffold {
            LaunchedEffect(Unit) {
                scope.launch {
                    fileList = fetchBackupFiles()
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (fileList.isEmpty()) {
                    Text(
                        text = "Não há backups",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(fileList.size) { index ->
                            FileItemView(file = fileList[index], onClick = { /* Ação ao clicar no item */ })
                        }
                    }
                }
            }
        }
    }
}
