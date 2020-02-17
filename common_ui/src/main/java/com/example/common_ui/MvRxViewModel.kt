package com.example.common_ui

import com.example.common_domain.error.MyError
import com.example.common_domain.result.Result
import com.example.common_domain.result.fold
import com.example.common_domain.useCase.NoParametersUseCase
import kotlinx.coroutines.SupervisorJob

abstract class MvRxViewModel<S : MvRxState>(
    initialState: S
) : BaseMvRxViewModel<S>(initialState) {

    protected val viewModelJob = SupervisorJob()

    fun <T : Any, V> NoParametersUseCase<T>.execute(
        mapper: ((T) -> V),
        stateReducer: S.(Async<V>) -> S
    ) {
        setState { stateReducer(Loading()) }
        this.invoke(viewModelJob) { result -> handleResult(result, mapper, stateReducer) }
    }

    private fun <T : Any, V> handleResult(
        result: Result<T>,
        mapper: ((T) -> V),
        stateReducer: S.(Async<V>) -> S
    ) =
        result.fold(
            error = { handleError(it, stateReducer) },
            success = {
                val success = Success(mapper(it))
                setState { stateReducer(success) }
            })

    private fun <V> handleError(error: MyError, stateReducer: S.(Async<V>) -> S) {
        setState { stateReducer(Fail(error)) }
    }
}