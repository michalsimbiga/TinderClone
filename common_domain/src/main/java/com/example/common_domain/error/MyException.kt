package com.example.common_domain.error

abstract class MyException : Exception() {
    abstract val originalException: Throwable?
}