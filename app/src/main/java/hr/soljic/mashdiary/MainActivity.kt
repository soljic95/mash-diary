package hr.soljic.mashdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hr.soljic.mashdiary.feature.home.navigation.SetupHomeRootNavHost
import hr.soljic.mashdiary.navigation.AuthenticationScreens
import hr.soljic.mashdiary.navigation.SetupRootNavGraph
import hr.soljic.mashdiary.ui.theme.MashDiaryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MashDiaryTheme {
                val navController = rememberNavController()
                SetupRootNavGraph(
                    startDestination = AuthenticationScreens.Authentication.route,
                    navController = navController
                )

            }
        }
    }
}
