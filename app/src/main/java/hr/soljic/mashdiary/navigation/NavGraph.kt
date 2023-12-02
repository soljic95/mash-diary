package hr.soljic.mashdiary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.stevdzasan.onetap.rememberOneTapSignInState
import hr.soljic.mashdiary.presentation.screens.AuthenticationScreen
import hr.soljic.mashdiary.presentation.screens.signin.SignInState
import hr.soljic.mashdiary.util.Constants

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController,
    onSignInClick: (() -> Unit)? = null
) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        authenticationRoute(onSignInClick)
        homeRoute()
        writeRoute()
    }

}

fun NavGraphBuilder.authenticationRoute(onSignInClick: (() -> Unit)?) {
    composable(route = Screen.Authentication.route) {
        AuthenticationScreen(SignInState(), onSignInClick!!)
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {

    }
}

fun NavGraphBuilder.writeRoute() {
    composable(
        route = Screen.Write.route,
        arguments = listOf(navArgument(name = Constants.WRITE_SCREEN_ARG) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) {

    }
}