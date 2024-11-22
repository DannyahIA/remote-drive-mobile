package remote.lunar.remotedrive.navigation

import FileScreen
import SharedScreen
import StarredScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import remote.lunar.remotedrive.ui.screens.drawer.*

@Composable
fun RemoteDriveNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "fileScreen") {

        // Tela principal de arquivos
        composable("fileScreen") { FileScreen(navController, context = LocalContext.current) }  // Corrigido para "fileScreen"
        composable("starredScreen") { StarredScreen(navController) }  // Corrigido para "starredScreen"
        composable("sharedScreen") { SharedScreen(navController) }  // Corrigido para "sharedScreen"

        // Telas acessadas pelo Drawer
        composable("backupScreen") { BackupScreen(navController) }  // Corrigido para "backupScreen"
        composable("offlineScreen") { OfflineScreen(navController) }  // Corrigido para "offlineScreen"
        composable("recentScreen") { RecentScreen(navController) }  // Corrigido para "recentScreen"
        composable("trashScreen") { TrashScreen(navController) }  // Corrigido para "trashScreen"
    }
}
