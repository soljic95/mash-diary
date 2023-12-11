package hr.soljic.mashdiary.feature.explore.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import hr.soljic.mashdiary.feature.explore.presentation.ExploreContent

@Composable
fun ExploreScreen(navController: NavController){
    ExploreContent(navController = navController)
}