package hr.soljic.mashdiary.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hr.soljic.mashdiary.feature.details.presentation.content.DetailsContent
import hr.soljic.mashdiary.feature.home.presentation.screen.HomeScreen

@Composable
fun SetupHomeRootNavHost(
) {
    val navController = rememberNavController()

    NavHost(
        startDestination = HomeScreens.Home.route,
        navController = navController,
    ) {
        home(navController = navController)
        details()
    }
}

fun NavGraphBuilder.home(navController: NavHostController) {
    composable(route = HomeScreens.Home.route) {
        HomeScreen(navController = navController)
    }
}

fun NavGraphBuilder.details() {
    composable(
        route = HomeScreens.Details.route
    ) { navBackStackEntry ->
        DetailsContent()
    }
}