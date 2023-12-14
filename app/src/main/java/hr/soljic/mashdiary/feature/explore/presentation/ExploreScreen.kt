package hr.soljic.mashdiary.feature.explore.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import hr.soljic.mashdiary.feature.explore.domain.model.ExploreItemModel
import hr.soljic.mashdiary.feature.explore.presentation.ExploreContent

@Composable
fun ExploreScreen(navController: NavController) {
    ExploreContent(navController = navController)
}
