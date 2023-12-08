package hr.soljic.mashdiary.feature.home.presentation.content

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import hr.soljic.mashdiary.feature.home.navigation.HomeScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    itemList: List<BottomNavigationItem>,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        itemList.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == bottomNavigationItem.route } == true,
                onClick = {
                    //changeSelectedItemIndex.invoke(index)
                    navController.navigate(bottomNavigationItem.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                },
                icon = {
                    BadgedBox(badge = {
                        if (bottomNavigationItem.badgeCount != 0) {
                            Text(text = bottomNavigationItem.badgeCount.toString())
                        } else if (bottomNavigationItem.hasNews) {
                            Badge()
                        }
                    }) {
                        Icon(
                            imageVector = if (currentDestination?.hierarchy?.any { it.route == bottomNavigationItem.route } == true) bottomNavigationItem.selectedIcon else bottomNavigationItem.unSelectedIcon,
                            contentDescription = bottomNavigationItem.title
                        )
                    }
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int = 0,
    val route: String
)

val bottomNavItemList = listOf(
    // ovo sigurno nije dobro haha
    BottomNavigationItem(
        title = "My library",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Default.Home,
        route = HomeScreens.MyLibrary.route
    ),
    BottomNavigationItem(
        title = "Explore",
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Default.Search,
        route = HomeScreens.Explore.route
    ),
    BottomNavigationItem(
        title = "Account",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Default.Person,
        route = HomeScreens.Account.route
    ),
)