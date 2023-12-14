package hr.soljic.mashdiary.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.soljic.mashdiary.core.Constants
import hr.soljic.mashdiary.feature.details.navigation.DetailsScreens
import hr.soljic.mashdiary.feature.account.AccountScreen
import hr.soljic.mashdiary.feature.explore.presentation.ExploreScreen
import hr.soljic.mashdiary.feature.mylibrary.MyLibraryScreen

@Composable
fun SetupHomeNavHost(
    navController: NavHostController,
    libraryExploreAccountNavController: NavHostController
) {


    NavHost(
        startDestination = HomeScreens.Explore.route,
        navController = libraryExploreAccountNavController,
    ) {
        explore(rootNavController = navController)
        myLibrary()
        account()
    }
}

fun NavGraphBuilder.myLibrary() {
    composable(route = HomeScreens.MyLibrary.route) {
        MyLibraryScreen()
    }
}

fun NavGraphBuilder.explore(rootNavController: NavHostController) {
    composable(route = HomeScreens.Explore.route) {
        ExploreScreen(navController = rootNavController)
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