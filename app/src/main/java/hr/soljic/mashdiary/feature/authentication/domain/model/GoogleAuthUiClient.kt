package hr.soljic.mashdiary.feature.authentication.domain.model

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hr.soljic.mashdiary.core.Constants
import hr.soljic.mashdiary.core.Constants.TAG
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(private val oneTapClient: SignInClient) {
    val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {

        val result = try {
            oneTapClient.beginSignIn(buildBeginSignInRequest()).await()

        } catch (e: Exception) {
            Log.d(TAG, "signIn: ${e.message} ")
            if (e is CancellationException) throw e
            null
        }
        Log.d(TAG, "result is null :${result == null} ")
        Log.d(TAG, "intent sender is null  :${result?.pendingIntent?.intentSender} ")
        return result?.pendingIntent?.intentSender
    }

    fun getGoogleAuthCredential(intent: Intent): AuthCredential{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken

        return GoogleAuthProvider.getCredential(googleIdToken, null)

    }

    suspend fun signInWithIntent(intent: Intent): SignInResult { // TODO ovaj dio te signina s firebaseaom. Kada ovo nemamo, zovemo nas normalni repository i api od fice
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


    suspend fun signOut() {
    }

    private fun buildBeginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.Builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(Constants.GOOGLE_WEB_CLIENT_ID)
                    .build()
            ).setAutoSelectEnabled(true)
            .build()
    }


}