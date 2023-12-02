package hr.soljic.mashdiary.presentation

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hr.soljic.mashdiary.presentation.screens.signin.SignInResult
import hr.soljic.mashdiary.presentation.screens.signin.UserData
import hr.soljic.mashdiary.util.Constants
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(private val oneTapClient: SignInClient) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {

        val result = try {
            oneTapClient.beginSignIn(buildBeginSignInRequest()).await()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            null
        }

        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken

        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            user?.run {
                SignInResult(
                    data = UserData(uid, displayName),
                    errorMessage = null
                )
            } ?: SignInResult(
                data = null,
                errorMessage = "User information not found"
            )

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    fun getSignedInUser(): UserData? = auth?.currentUser?.run {
        UserData(uid, displayName)
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
        }

    }

    private fun buildBeginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.Builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(Constants.GOOGLE_WEB_CLIENT_ID)
                    .build()
            ).setAutoSelectEnabled(true)
            .build()
    }


}