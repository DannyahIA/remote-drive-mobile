package remote.lunar.remotedrive.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Starred,
        BottomNavItem.Shared,
        BottomNavItem.Files
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = { Text(item.label, maxLines = 1, softWrap = false, style = MaterialTheme.typography.labelSmall) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String) {
    object Home : BottomNavItem("Início", Icons.Default.Home, "HomeScreen")
    object Starred : BottomNavItem("Com estrela", Icons.Default.Star, "StarredScreen")
    object Shared : BottomNavItem("Compartilhado", Icons.Default.Share, "SharedScreen")
    object Files : BottomNavItem("Arquivos", Icons.Default.Folder, "FileScreen")
}

@Preview
@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController = navController)
}
