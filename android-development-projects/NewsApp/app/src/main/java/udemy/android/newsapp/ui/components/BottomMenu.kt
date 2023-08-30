package udemy.android.newsapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import udemy.android.newsapp.R
import udemy.android.newsapp.ui.BottomMenuScreen

@Composable
fun BottomMenu(navController: NavController) {
    val menuItems = listOf(
        BottomMenuScreen.TopNews, BottomMenuScreen.Categories, BottomMenuScreen.Sources
    )

    BottomNavigation(contentColor = colorResource(R.color.white)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        menuItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == it.route,
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}