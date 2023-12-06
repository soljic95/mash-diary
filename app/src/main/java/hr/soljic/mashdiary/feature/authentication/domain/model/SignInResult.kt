package hr.soljic.mashdiary.feature.authentication.domain.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?,
)

data class UserData(
    val userId: String?,
    val userName: String?
)