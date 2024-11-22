import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import remote.lunar.remotedrive.data.model.FileItem

@Composable
fun FileItemView(file: FileItem, onClick: () -> Unit, onDelete: (FileItem) -> Unit, onEdit: (FileItem) -> Unit) {
    var expanded by remember { mutableStateOf(false) } // Controle do menu

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone para pastas ou arquivos
        val icon = if (file.Type == "folder") Icons.Filled.Folder else Icons.Filled.InsertDriveFile
        val iconColor = if (file.Type == "folder") Color(0xFFFFD700) else Color(0xFF6200EE)

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
                text = file.ItemName,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Última modificação: ${file.UpdateAt}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        IconButton(
            onClick = { expanded = !expanded } // Controlar o estado do menu
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Opções")
        }

        // Menu com opções de editar e excluir
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Fecha o menu quando clicar fora
        ) {
            DropdownMenuItem(
                text = { Text("Editar") },
                onClick = {
                    onEdit(file) // Chama a função de edição
                    expanded = false // Fecha o menu
                }
            )
            DropdownMenuItem(
                text = { Text("Excluir") },
                onClick = {
                    onDelete(file) // Chama a função de exclusão
                    expanded = false // Fecha o menu
                }
            )
        }
    }
}
