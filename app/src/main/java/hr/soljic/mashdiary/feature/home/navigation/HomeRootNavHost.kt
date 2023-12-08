package hr.soljic.mashdiary.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.soljic.mashdiary.feature.home.presentation.screen.HomeScreen

@Composable
fun SetupHomeRootNavHost(
) {
    val navController = rememberNavController()

    NavHost(
        startDestination = HomeScreens.Home.route,
        navController = navController,
    ) {
        home()
    }
}

fun NavGraphBuilder.home() {
    composable(route = HomeScreens.Home.route) {
        HomeScreen()
    }
}