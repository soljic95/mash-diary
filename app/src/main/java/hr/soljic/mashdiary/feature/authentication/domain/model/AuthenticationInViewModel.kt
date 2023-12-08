package hr.soljic.mashdiary.feature.authentication.domain.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.soljic.mashdiary.feature.authentication.domain.use_case.AuthenticationUseCaseWrapper
import hr.soljic.mashdiary.feature.authentication.presentation.sign_in.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationInViewModel @Inject constructor(
    private val authenticationUseCaseWrapper: AuthenticationUseCaseWrapper
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private fun onSignInResult(signInResult: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = signInResult.data != null,
                signInError = signInResult.errorMessage
            )
        }
    }

    fun signInUserWithGoogleAuth(authCredential: AuthCredential? = null) {
        viewModelScope.launch {
            val result = authenticationUseCaseWrapper.googleSignInUseCase.invoke(authCredential)
            result.collect { signInResult ->
                signInResult?.let {
                    onSignInResult(signInResult = it)
                }
            }
        }
    }

    fun setStateToLoading() {
        _state.update {
            SignInState(loading = true)
        }
    }

    fun resetState() {
        _state.update {
            SignInState()
        }
    }
}