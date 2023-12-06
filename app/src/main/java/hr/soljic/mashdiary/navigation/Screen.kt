package hr.soljic.mashdiary.navigation

import hr.soljic.mashdiary.core.Constants.WRITE_SCREEN_ARG

sealed class Screen(val route: String) {
    object Authentication :
        Screen(route = "authentication_screen") // no reason not to use an object for a class that holds no state

    object Home : Screen(route = "home_screen")
    object Write : Screen(route = "write_screen?$WRITE_SCREEN_ARG={$WRITE_SCREEN_ARG}") {
        fun passDiaryId(diaryId: String) = "write_screen?$WRITE_SCREEN_ARG$diaryId"
    }
}
