package hr.soljic.mashdiary.di

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.soljic.mashdiary.core.Constants.REQUEST_TIMEOUT
import hr.soljic.mashdiary.core.Constants.RESPONSE_TIMEOUT
import hr.soljic.mashdiary.feature.authentication.data.data_source.remote.AuthService
import hr.soljic.mashdiary.feature.authentication.data.repository.AuthenticationRepository
import hr.soljic.mashdiary.feature.authentication.data.repository.AuthenticationRepositoryImpl
import hr.soljic.mashdiary.feature.authentication.domain.use_case.AuthenticationUseCaseWrapper
import hr.soljic.mashdiary.feature.authentication.domain.use_case.GoogleSignInUseCase
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val TIMEOUT_TIME_UNIT = TimeUnit.SECONDS
//todo find out how to split modules

    @Provides
    @Singleton
    fun provideOkHttpClient(dateNightInterceptor: DateNightInterceptor): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(RESPONSE_TIMEOUT, TIMEOUT_TIME_UNIT)
            .dispatcher(Dispatcher().apply { maxRequests = 10 })
            .addInterceptor(dateNightInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .writeTimeout(REQUEST_TIMEOUT, TIMEOUT_TIME_UNIT)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://google.com")
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit, okHttpClient: OkHttpClient): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInUseCase(authRepository: AuthenticationRepository): GoogleSignInUseCase {
        return GoogleSignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideDateNightInterceptor(): DateNightInterceptor {
        return DateNightInterceptor()
    }

    @Provides
    fun provideAuthenticationUseCaseWrapper(googleSignInUseCase: GoogleSignInUseCase): AuthenticationUseCaseWrapper {
        return AuthenticationUseCaseWrapper(googleSignInUseCase)
    }

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authService)
    }

    class DateNightInterceptor(
    ) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val locale = AppCompatDelegate.getApplicationLocales().get(0)
            val locale2: Locale? = Locale.getDefault()


            Log.d("locale", "size : ${AppCompatDelegate.getApplicationLocales().size()}")
            Log.d(
                "locale",
                "intercept: ${locale.toString().plus("-${locale?.language?.uppercase()}")}"
            )

            var acceptLanguage = "en-En"

            acceptLanguage = if (locale != null) {
                if (locale.toString().contains("_")) locale.toString()
                    .replace("_", "-") else locale.toString()
                    .plus("-${locale.language.uppercase()}")
            } else {
                if (locale2.toString().contains("_")) locale2.toString()
                    .replace("_", "-") ?: "en-En" else locale.toString()
                    .plus("-${locale2?.language?.uppercase()}")
            }

            requestBuilder.addHeader(
                "Accept-Language", acceptLanguage
            )


            // runBlocking {
            //     sessionManager.getAuthObject()?.let {
            //         requestBuilder.addHeader("Authorization", "Bearer ${it.accessToken}")
            //     }
            // }
            return chain.proceed(requestBuilder.build())
        }
    }

}