package hr.soljic.mashdiary.feature.authentication.presentation.sign_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val loading: Boolean = false


)