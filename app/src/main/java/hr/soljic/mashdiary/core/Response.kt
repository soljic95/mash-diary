package hr.soljic.mashdiary.core

sealed class Response<T>(val data: T?, val message: String?) {
    class Success<T>(data: T?, message: String?= null) : Response<T>(data, message)
    class Error<T>(data: T? = null, message: String? = "An error occurred") : Response<T>(data, message)
    class Loading<T>() : Response<T>(null, null)
}