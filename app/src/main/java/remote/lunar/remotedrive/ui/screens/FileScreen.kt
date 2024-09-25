import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import remote.lunar.remotedrive.data.model.FileItem
import remote.lunar.remotedrive.data.remote.fetchRootFiles

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FileScreen(navController: NavController) {
    var fileList by remember { mutableStateOf<List<FileItem>>(emptyList()) }
    val scope = rememberCoroutineScope()

    Scaffold {
        LaunchedEffect(Unit) {
            scope.launch {
                fileList = fetchRootFiles()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (fileList.isEmpty()) {
                Text(
                    text = "Nenhum arquivo disponível",
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

@Composable
fun FileItemView(file: FileItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone para pastas ou arquivos
        val icon = if (file.IsFolder) Icons.Filled.Folder else Icons.Filled.InsertDriveFile
        val iconColor = if (file.IsFolder) Color(0xFFFFD700) else Color(0xFF6200EE)

        Icon(
            icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = file.Name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Última modificação: ${file.DateModified}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        IconButton(onClick = { /* Menu de opções */ }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Opções")
        }
    }
}