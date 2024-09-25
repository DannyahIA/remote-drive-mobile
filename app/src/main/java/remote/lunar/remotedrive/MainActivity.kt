@file:OptIn(ExperimentalMaterial3Api::class)

package remote.lunar.remotedrive

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import remote.lunar.remotedrive.navigation.RemoteDriveNavHost
import remote.lunar.remotedrive.ui.components.AppDrawer
import remote.lunar.remotedrive.ui.components.BottomNavBar
import remote.lunar.remotedrive.ui.theme.RemoteDriveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemoteDriveTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()

                MainScreen(navController, drawerState, coroutineScope)
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    // Verifique se estamos em uma tela acessada via Drawer
    val isDrawerScreen = when (currentDestination) {
        "BackupScreen", "HelpFeedbackScreen", "OfflineScreen", "RecentScreen",
        "SettingScreen", "StorageScreen", "TrashScreen" -> true
        else -> false
    }

    // Drawer layout
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(navController = navController, onCloseDrawer = {
                coroutineScope.launch {
                    drawerState.close()
                }
            })
        }
    ) {
        Scaffold(
            // Exibe ou oculta a TopAppBar e BottomNav dependendo se estamos em uma tela do Drawer
            topBar = {
                if (!isDrawerScreen) {
                    TopAppBar(
                        title = { Text(text = "RemoteDrive") },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                            }
                        }
                    )
                }
            },
            bottomBar = {
                if (!isDrawerScreen) {
                    BottomNavBar(navController)
                }
            }
        ) {
            RemoteDriveNavHost(navController = navController)
        }
    }
}
