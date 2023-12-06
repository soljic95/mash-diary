package hr.soljic.mashdiary.feature.authentication.domain.use_case

import javax.inject.Inject

class AuthenticationUseCaseWrapper @Inject constructor(
    val googleSignInUseCase: GoogleSignInUseCase
) {
}