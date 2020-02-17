package com.example.common_domain.error

sealed class MyError : MyException() {
    data class ServerError(override val originalException: Throwable? = null) : MyError()
    data class ServerUnavailable(override val originalException: Throwable? = null) : MyError()
    data class Unauthorized(override val originalException: Throwable? = null) : MyError()
    data class NoBody(override val originalException: Throwable? = null) : MyError()
    data class TokenNotFound(override val originalException: Throwable? = null) : MyError()

    data class DataBaseNameNotFound(override val originalException: Throwable? = null) :
        MyError()

    data class Unknown(override val originalException: Throwable? = null) : MyError()
    data class RefreshTokenExpired(override val originalException: Throwable? = null) :
        MyError()

    data class EntityNotFound(override val originalException: Throwable? = null) : MyError()
    data class ValidationError(override val originalException: Throwable? = null) : MyError()
}