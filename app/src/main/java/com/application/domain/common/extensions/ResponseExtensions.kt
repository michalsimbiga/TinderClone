package com.application.domain.common.extensions

import com.application.domain.common.error.MyApiException
import retrofit2.Response

fun <A : Any> Response<A>.bodyOrException(): A {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw MyApiException(this)
    }
}