package hr.soljic.mashdiary.feature.home.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hr.soljic.mashdiary.feature.home.domain.model.HomeViewModel
import hr.soljic.mashdiary.feature.home.navigation.HomeScreens
import hr.soljic.mashdiary.feature.home.navigation.SetupHomeNavHost
import hr.soljic.mashdiary.feature.home.presentation.content.BottomNavigationBar
import hr.soljic.mashdiary.feature.home.presentation.content.bottomNavItemList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val libraryExploreAccountNavController =
        rememberNavController() //this navController should control explore,library and account
    val navBackStackEntry by libraryExploreAccountNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        topBar = {
            if (currentDestination?.route == HomeScreens.Explore.route) {
                ExpandableSearchView()
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = libraryExploreAccountNavController,
                backStackEntry = navBackStackEntry,
                currentDestination = currentDestination,
                itemList = bottomNavItemList
            )
        },
        content = {
            SetupHomeNavHost(
                navController = navController,
                libraryExploreAccountNavController = libraryExploreAccountNavController
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableSearchView() {
    var searchQuery by remember {
        mutableStateOf("")
    }
    var focused by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 12.dp)
                .onFocusChanged {
                    focused = it.isFocused
                }
                .fillMaxWidth(if (focused) 0.9f else 0.5f)
                .animateContentSize()
                .align(Alignment.Center),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Icon"
                )
            },
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            placeholder = { Text(text = "Explore") },
            shape = RoundedCornerShape(20.dp),
        )
    }

}
