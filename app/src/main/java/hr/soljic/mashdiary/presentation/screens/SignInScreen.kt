package hr.soljic.mashdiary.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import hr.soljic.mashdiary.presentation.screens.signin.AuthenticationContent
import hr.soljic.mashdiary.presentation.screens.signin.SignInState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    signInState: SignInState,
    onSignInClick: () -> Unit,
) {
    val context = LocalContext.current


    Scaffold(content = {
        AuthenticationContent(
             onActionClick = { onSignInClick() }
        )
    })

}


