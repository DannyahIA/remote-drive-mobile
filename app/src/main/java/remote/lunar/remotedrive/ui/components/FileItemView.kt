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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import remote.lunar.remotedrive.data.model.FileItem

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