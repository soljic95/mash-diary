package hr.soljic.mashdiary.feature.authentication.domain.use_case

import com.google.firebase.auth.AuthCredential
import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.authentication.domain.model.SignInResult
import hr.soljic.mashdiary.feature.authentication.data.repository.AuthenticationRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(authCredential: AuthCredential? = null): Flow<SignInResult?> {
        val result: Flow<Response<SignInResult>> = try {
            repository.googleSignIn(authCredential)

        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            } else {
                flow {
                    emit(Response.Error(message = e.message))
                }

            }
        }

        return result.map { it.data }

    }

}