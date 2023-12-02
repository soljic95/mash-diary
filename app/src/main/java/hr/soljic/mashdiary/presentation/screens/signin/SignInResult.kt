package hr.soljic.mashdiary.presentation.screens.signin

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?,
)

data class UserData(
    val userId: String?,
    val userName: String?
)