package hr.soljic.mashdiary.feature.authentication.data.repository

import com.google.firebase.auth.AuthCredential
import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.authentication.domain.model.SignInResult
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun googleSignIn(credential: AuthCredential): Flow<Response<SignInResult>>

    suspend fun saveLoginToken(token: String?)
    suspend fun getLoginToken(): String?
}