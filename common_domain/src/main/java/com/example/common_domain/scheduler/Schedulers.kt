package com.example.common_domain.scheduler

import kotlinx.coroutines.CoroutineDispatcher

interface Schedulers {
    fun io(): CoroutineDispatcher
    fun background(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
