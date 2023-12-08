package hr.soljic.mashdiary.feature.home.navigation

sealed class HomeScreens(val route: String) {
    object Home :
        HomeScreens(route = "home_screen")

    object Explore : HomeScreens(route = "explore_screen")
    object MyLibrary : HomeScreens(route = "my_library_screen")
    object Account : HomeScreens(route = "account_screen")
}
