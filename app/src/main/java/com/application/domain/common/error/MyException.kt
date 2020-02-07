package com.application.domain.common.error

abstract class MyException : Exception() {
    abstract val originalException: Throwable?
}