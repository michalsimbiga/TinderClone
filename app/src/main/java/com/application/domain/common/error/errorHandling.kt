package com.application.domain.common.error

import java.io.IOException
import java.net.HttpURLConnection
import kotlin.NullPointerException

private const val ENTITY_NOT_FOUND = "ResultSet returned null"
private const val REFRESH_TOKEN_EXPIRED = "Refresh token expired"

fun Throwable.toMyError(): MyError =
    when (this) {
        is IOException -> toMyError()
        is NullPointerException -> toMyError()
        is MyApiException -> toMyError()
        else -> MyError.Unknown(this)
    }

internal fun NullPointerException.toMyError(): MyError =
    MyError.EntityNotFound(this)

internal fun MyApiException.toMyError(): MyError = when {
    this.code == HttpURLConnection.HTTP_UNAUTHORIZED -> MyError.Unauthorized(this)
    this.code == HttpURLConnection.HTTP_NO_CONTENT -> MyError.NoBody(this)
    this.code == HttpURLConnection.HTTP_BAD_REQUEST -> badRequestToMyError(this)
    this.code >= HttpURLConnection.HTTP_INTERNAL_ERROR -> MyError.ServerError(this)
    else -> MyError.ServerError(this)
}

private fun badRequestToMyError(apiException: MyApiException): MyError =
    if (apiException.message?.contains(REFRESH_TOKEN_EXPIRED) == true) {
        MyError.RefreshTokenExpired(apiException)
    } else {
        MyError.ServerError(apiException)
    }