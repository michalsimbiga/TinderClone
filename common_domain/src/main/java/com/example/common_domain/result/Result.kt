package com.example.common_domain.result

import com.example.common_domain.error.MyError
import com.example.common_domain.error.toMyError

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: MyError) : Result<Nothing>()
}

inline fun <T> safeCall(call: () -> T): Result<T> =
    try {
        success(call.invoke())
    } catch (exception: Exception) {
        failure(exception.toMyError())
    }

fun <T> success(data: T) = Result.Success(data)
fun failure(myError: MyError) =
    Result.Failure(myError)

inline infix fun <T> Result<T>.doOnSuccess(f: (T) -> Unit): Result<T> {
    if (this is Result.Success) f(data)
    return this
}

inline infix fun <T> Result<T>.doOnFailure(f: (MyError) -> Unit): Result<T> {
    if (this is Result.Failure) f(error)
    return this
}


@Suppress("UNCHECKED_CAST")
inline fun <T, A> Result<T>.fold(
    error: (MyError) -> A = { this as A },
    success: (T) -> A = { this as A }
): A =
    when (this) {
        is Result.Failure -> error(this.error)
        is Result.Success -> success(this.data)
    }