package remote.lunar.remotedrive.navigation

import FileScreen
import HomeScreen
import SharedScreen
import StarredScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import remote.lunar.remotedrive.ui.screens.drawer.*

@Composable
fun RemoteDriveNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "fileScreen") {

        // Tela principal de arquivos
        composable("FileScreen") { FileScreen(navController) }
        composable("HomeScreen") { HomeScreen(navController) }
        composable("StarredScreen") { StarredScreen(navController) }
        composable("SharedScreen") { SharedScreen(navController) }


        // Telas acessadas pelo Drawer
        composable("BackupScreen") { BackupScreen(navController) }
        composable("HelpFeedbackScreen") { HelpFeedbackScreen(navController) }
        composable("OfflineScreen") { OfflineScreen(navController) }
        composable("RecentScreen") { RecentScreen(navController) }
        composable("SettingScreen") { SettingScreen(navController) }
        composable("StorageScreen") { StorageScreen(navController) }
        composable("TrashScreen") { TrashScreen(navController) }
    }
}
