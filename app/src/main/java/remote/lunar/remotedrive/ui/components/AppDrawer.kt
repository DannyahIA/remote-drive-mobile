package remote.lunar.remotedrive.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import remote.lunar.remotedrive.ui.theme.RemoteDriveTheme

@Composable
fun AppDrawer(
    navController: NavHostController,
    onCloseDrawer: () -> Unit
) {
    // Drawer principal com estilo e espaçamento ajustados
    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerShape = MaterialTheme.shapes.medium, // Ajustar a forma para suavizar
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Título "Google Drive"
            Text(
                text = "Remote Drive",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

            // Ícones e textos como no exemplo
            DrawerItem(
                label = "Recentes",
                icon = Icons.Default.History,
                onClick = {
                    navController.navigate("RecentScreen")
                    onCloseDrawer()
                }
            )
            DrawerItem(
                label = "Off-line",
                icon = Icons.Default.CheckCircle,
                onClick = {
                    navController.navigate("OfflineScreen")
                    onCloseDrawer()
                }
            )
            DrawerItem(
                label = "Lixeira",
                icon = Icons.Default.Delete,
                onClick = {
                    navController.navigate("TrashScreen")
                    onCloseDrawer()
                }
            )
            DrawerItem(
                label = "Backups",
                icon = Icons.Default.Backup,
                onClick = {
                    navController.navigate("BackupScreen")
                    onCloseDrawer()
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Informação de armazenamento
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "9,04 GB de 15 GB usados",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                LinearProgressIndicator(
                    progress = { 9.04f / 15f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color(0xFFEA4335), // Cor semelhante ao da imagem
                )
            }
        }
    }
}

@Composable
fun DrawerItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun AppDrawerPreview() {
    val navController = rememberNavController()
    RemoteDriveTheme {
        AppDrawer(navController = navController, onCloseDrawer = {})
    }
}
