import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearch(
    onDrawerClick: () -> Unit,
) {
    var isSearchActive by remember { mutableStateOf(false) }  // Controla o estado da barra de pesquisa
    var query by remember { mutableStateOf("") }  // Controla o texto da pesquisa

    // Verifica se a busca está ativa
    if (isSearchActive) {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                // Ação de pesquisa
                println("Buscando por: $it")
                isSearchActive = false // Fechar a barra de pesquisa após pesquisar (opcional)
            },
            active = isSearchActive,
            onActiveChange = {
                isSearchActive = it // Define quando a barra está ativa ou não
            },
            placeholder = { Text("Buscar arquivos...") },
            leadingIcon = {
                IconButton(onClick = { isSearchActive = false }) {
                    Icon(Icons.Default.Menu, contentDescription = "Voltar")
                }
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(Icons.Default.Search, contentDescription = "Limpar")
                    }
                }
            }
        ) {
            // Aqui você pode colocar sugestões ou histórico de pesquisa
            Text("Sugestões aparecem aqui")
        }
    } else {
        // A `TopAppBar` normal quando a busca não está ativa
        TopAppBar(
            title = { Text(text = "RemoteDrive") },
            navigationIcon = {
                IconButton(onClick = onDrawerClick) {
                    Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                }
            },
            actions = {
                // Ícone de busca na `TopAppBar`
                IconButton(onClick = { isSearchActive = true }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
    }
}
