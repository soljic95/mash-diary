package hr.soljic.mashdiary.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.stevdzasan.onetap.OneTapSignInState
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import hr.soljic.mashdiary.presentation.screens.auth.AuthenticationContent
import hr.soljic.mashdiary.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    loadingState: Boolean
) {
    Scaffold(content = {
        AuthenticationContent(
            loadingState = loadingState, onActionClick = {
            }
        )
    })

}


