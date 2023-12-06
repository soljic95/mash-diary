package hr.soljic.mashdiary.feature.authentication.presentation.sign_in

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.AuthCredential
import hr.soljic.mashdiary.core.Constants.TAG
import hr.soljic.mashdiary.feature.authentication.domain.model.GoogleAuthUiClient
import hr.soljic.mashdiary.feature.authentication.domain.model.AuthenticationInViewModel
import hr.soljic.mashdiary.feature.authentication.domain.model.SignInResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val googleAuthUiClient = GoogleAuthUiClient(Identity.getSignInClient(LocalContext.current))
    var activityResultJob: Job?
    var getSignIntentSenderJob: Job?


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val authCredential = result.data?.let {
                    googleAuthUiClient.getGoogleAuthCredential(
                        intent = it
                    )
                }
                authCredential?.let { viewModel.signInUserWithGoogleAuth(authCredential = it) }
                viewModel.resetState()
            } else {
                viewModel.resetState()
            }
        })

    Scaffold(content = {
        AuthenticationContent(
            signInState = state,
            onActionClick = {
                Log.d(TAG, "Sending SignInIntentSender : ")
                getSignIntentSenderJob = CoroutineScope(Dispatchers.IO).launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    Log.d(TAG, "SignInIntentSender is null :${signInIntentSender == null} ")
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            intentSender = signInIntentSender ?: return@launch
                        ).build()
                    )
                    this.coroutineContext.cancel()
                    viewModel.setStateToLoading()
                }
            }
        )
    })
}
