package hr.soljic.mashdiary.feature.details.navigation

sealed class DetailsScreens(val route: String) {

    object DateNightDetail : DetailsScreens(route = "date_night_detail_screen")
}