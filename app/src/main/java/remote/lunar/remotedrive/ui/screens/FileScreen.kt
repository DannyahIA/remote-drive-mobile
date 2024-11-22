import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import remote.lunar.remotedrive.data.local.AppDatabase
import remote.lunar.remotedrive.data.model.FileItem
import remote.lunar.remotedrive.data.remote.RemoteDriveApi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FileScreen(navController: NavController, context: Context) {
    var fileList by remember { mutableStateOf<List<FileItem>>(emptyList()) }
    val scope = rememberCoroutineScope() // Escopo de corrotina

    // Função para atualizar a lista de arquivos
    fun updateFileList() {
        scope.launch {
            fileList = fetchLocalFiles(context) // Atualiza a lista de arquivos locais
        }
    }

    // Função chamada ao adicionar um novo arquivo manualmente (sem file picker)
    fun addNewFile(name: String) {
        scope.launch {
            val newFile = FileItem(
                ItemId = "id_${System.currentTimeMillis()}",
                OwnerId = "local_user",
                ItemName = name,
                ItemPath = "path_not_necessary", // Não estamos lidando com o caminho real do arquivo
                Type = "file",
                Size = "0", // Tamanho padrão
                UpdateAt = System.currentTimeMillis().toString(),
                CreatedAt = System.currentTimeMillis().toString()
            )
            // Adiciona ao banco de dados
            addFileToLocalDatabase(context, newFile)
            // Atualiza a lista de arquivos após adição
            updateFileList()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Função para adicionar manualmente
                addNewFile("Novo Arquivo") // Substitua isso por um prompt para entrada de nome se necessário
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Arquivo")
            }
        }
    ) {
        // Atualiza a lista de arquivos inicialmente
        LaunchedEffect(Unit) {
            updateFileList() // Busca arquivos ao inicializar
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
                        FileItemView(
                            file = fileList[index],
                            onClick = { /* Ação ao clicar no item */ },
                            onDelete = { file ->
                                scope.launch {
                                    deleteFile(file, context)
                                    updateFileList() // Atualiza a lista após excluir
                                }
                            },
                            onEdit = { file ->
                                scope.launch {
                                    editFile(file, context)
                                    updateFileList() // Atualiza a lista após editar
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// Função para adicionar o arquivo no banco de dados local
suspend fun addFileToLocalDatabase(context: Context, file: FileItem) {
    val database = AppDatabase.getInstance(context)  // Aqui, passamos o contexto explicitamente
    database.fileItemDao().insertsFileItems(listOf(file)) // Insere o arquivo no banco
}

// Função para buscar arquivos locais
suspend fun fetchLocalFiles(context: Context): List<FileItem> {
    val database = AppDatabase.getInstance(context)
    return database.fileItemDao().getAllFileItems().first() // Retorna os arquivos locais
}

suspend fun deleteFile(file: FileItem, context: Context) {
    // Exclui o arquivo do banco local
    val database = AppDatabase.getInstance(context)
    database.fileItemDao().removeFileItem(file)

    // Sincronize com a API para deletar o arquivo
    syncFileDeletionWithAPI(file)
}

suspend fun editFile(file: FileItem, context: Context) {
    // Abre uma tela ou caixa de diálogo para editar o arquivo (nome, tipo, etc.)
    val updatedFile = file.copy(ItemName = "Novo Nome") // Atualize conforme necessário

    // Atualiza o arquivo no banco local
    val database = AppDatabase.getInstance(context)
    database.fileItemDao().updateFileItem(updatedFile)

    // Sincronize a alteração com a API
    syncFileUpdateWithAPI(updatedFile)
}

suspend fun syncFileDeletionWithAPI(file: FileItem) {
    // Exemplo de sincronização com a API para deletar
    RemoteDriveApi.deleteFile(file.ItemId)
}

suspend fun syncFileUpdateWithAPI(file: FileItem) {
    // Exemplo de sincronização com a API para atualizar
    RemoteDriveApi.updateFile(file.ItemId, file)
}