package com.example.common_domain.useCase

import com.example.common_domain.result.Result
import com.example.common_domain.scheduler.DefaultSchedulers
import com.example.common_domain.scheduler.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Base use case class with parameters, handles the coroutine context switching
 * It also allows you to scope it to required job (most of the time viewmodel's)
 *
 * @param schedulers - [DefaultSchedulers] as default value,
 * to switch schedulers, pass the proper ones through the constructor
 * ```
 *    class LoginUseCase(
 *        schedulers: Schedulers,
 *        ...
 *    ) : UseCase<User, LoginUseCase.Params>(
 *        schedulers = schedulers
 *    )
 * ```
 */

abstract class UseCase<out Type, in Params>(
    schedulers: Schedulers = DefaultSchedulers()
) where Type : Any {
    var backgroundContext: CoroutineContext = schedulers.background()
    var foregroundContext: CoroutineContext = schedulers.main()

    abstract suspend fun run(params: Params): Result<Type>

    /**
     * Invokes the use case
     *
     * You can scope the work by passing the coroutine job from parent
     *
     * @param parentJob - parent coroutine Job, useful when you are calling this from ViewModel
     */
    operator fun invoke(
        parentJob: Job = Job(),
        params: Params,
        onResult: (Result<Type>) -> Unit = {}
    ) {
        CoroutineScope(backgroundContext + parentJob).launch {
            val result = run(params)
            withContext(foregroundContext) {
                onResult(result)
            }
        }
    }
}

fun <T : UseCase<*, *>> T.makeUnconfined() = apply {
    backgroundContext = Dispatchers.Unconfined
    foregroundContext = Dispatchers.Unconfined
}
