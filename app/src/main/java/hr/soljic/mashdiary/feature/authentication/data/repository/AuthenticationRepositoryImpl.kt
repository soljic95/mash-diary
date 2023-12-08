package hr.soljic.mashdiary.feature.authentication.data.repository

import com.google.firebase.auth.AuthCredential
import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.authentication.data.data_source.remote.AuthService
import hr.soljic.mashdiary.feature.authentication.domain.model.SignInResult
import hr.soljic.mashdiary.feature.authentication.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authService: AuthService // inject data store also and room
) : AuthenticationRepository {
    override suspend fun googleSignIn(credential: AuthCredential?): Flow<Response<SignInResult>> {
        return flow {
            emit(Response.Loading())
            //here we would call the auth service
           // val result = authService.signInWithGoogleCredentials(credential)
            kotlinx.coroutines.delay(500)
            emit(
                Response.Success(
                    data = SignInResult(
                        data = UserData("1", "Marko Soljic"),
                        errorMessage = null
                    )
                )
            )
        }.catch {
            emit(Response.Error(message = it.message))
        }
    }

    override suspend fun saveLoginToken(token: String?) {

    }

    override suspend fun getLoginToken(): String? {
        return ""
    }
}