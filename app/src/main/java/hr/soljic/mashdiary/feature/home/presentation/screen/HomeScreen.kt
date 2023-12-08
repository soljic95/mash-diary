package hr.soljic.mashdiary.feature.home.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hr.soljic.mashdiary.feature.home.domain.model.HomeViewModel
import hr.soljic.mashdiary.feature.home.navigation.HomeScreens
import hr.soljic.mashdiary.feature.home.navigation.SetupHomeNavHost
import hr.soljic.mashdiary.feature.home.presentation.content.BottomNavigationBar
import hr.soljic.mashdiary.feature.home.presentation.content.BottomNavigationItem
import hr.soljic.mashdiary.feature.home.presentation.content.bottomNavItemList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                itemList = bottomNavItemList
            )
        },
        content = {
            SetupHomeNavHost(navController = navController)
        }
    )
}