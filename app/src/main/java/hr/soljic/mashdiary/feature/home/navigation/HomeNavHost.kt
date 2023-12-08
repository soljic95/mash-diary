package hr.soljic.mashdiary.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import hr.soljic.mashdiary.core.Constants
import hr.soljic.mashdiary.feature.details.navigation.DetailsScreens
import hr.soljic.mashdiary.feature.home.presentation.screen.AccountScreen
import hr.soljic.mashdiary.feature.home.presentation.screen.ExploreScreen
import hr.soljic.mashdiary.feature.home.presentation.screen.MyLibraryScreen

@Composable
fun SetupHomeNavHost(
    navController: NavHostController
) {

    NavHost(
        startDestination = HomeScreens.Explore.route,
        navController = navController,
    ) {
        explore()
        myLibrary()
        account()
    }
}

fun NavGraphBuilder.myLibrary() {
    composable(route = HomeScreens.MyLibrary.route) {
        MyLibraryScreen()
    }
}

fun NavGraphBuilder.explore() {
    composable(route = HomeScreens.Explore.route) {
        ExploreScreen()

    }
}

fun NavGraphBuilder.account() {
    composable(
        route = HomeScreens.Account.route,
        arguments = listOf(navArgument(name = Constants.WRITE_SCREEN_ARG) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) {
        AccountScreen()
    }
}

fun NavGraphBuilder.detailsGraph() {
    composable(
        route = "details_route"
    ) {
        NavHost(
            startDestination = DetailsScreens.DateNightDetail.route,
            navController = rememberNavController()
        ) {

        }
    }
}