package hr.soljic.mashdiary.navigation

import hr.soljic.mashdiary.core.Constants.WRITE_SCREEN_ARG

sealed class AuthenticationScreens(val route: String) {
    object Authentication :
        AuthenticationScreens(route = "authentication_screen") // no reason not to use an object for a class that holds no state
}
