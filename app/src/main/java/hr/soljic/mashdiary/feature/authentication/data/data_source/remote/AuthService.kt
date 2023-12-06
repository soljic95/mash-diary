package hr.soljic.mashdiary.feature.authentication.data.data_source.remote

import com.google.firebase.auth.AuthCredential
import hr.soljic.mashdiary.feature.authentication.domain.model.SignInResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {
    @GET("auth/google")
    suspend fun signInWithGoogleCredentials(
        @Body googleAuthCredential: AuthCredential
    ): Response<hr.soljic.mashdiary.core.Response<SignInResult>>
}