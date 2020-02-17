package com.example.common_domain.extensions

import com.example.common_domain.error.MyApiException
import retrofit2.Response

fun <A : Any> Response<A>.bodyOrException(): A {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw MyApiException(this)
    }
}