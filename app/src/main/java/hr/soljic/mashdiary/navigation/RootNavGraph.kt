package hr.soljic.mashdiary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.soljic.mashdiary.feature.authentication.presentation.sign_in.AuthenticationScreen
import hr.soljic.mashdiary.feature.home.navigation.HomeScreens
import hr.soljic.mashdiary.feature.home.navigation.SetupHomeRootNavHost

@Composable
fun SetupRootNavGraph( // uvodni NavHost, unutar sebe ima nestani navigationComposable s root id-em "home_destination_route"
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        startDestination = startDestination,
        navController = navController,
    ) {
        authenticationRoute(navController)
        navigation(
            startDestination = HomeScreens.Home.route,
            route = "home_destination_route"
        ) {//todo smooth this out
            composable(route = HomeScreens.Home.route) {
                SetupHomeRootNavHost()
            }
        }
    }
}

fun NavGraphBuilder.authenticationRoute(navController: NavController) {
    composable(route = AuthenticationScreens.Authentication.route) {
        AuthenticationScreen(navController = navController)
    }
}
