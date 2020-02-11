package com.application.ui.base

import com.airbnb.mvrx.*
import com.application.domain.common.error.MyError
import com.application.domain.common.result.Result
import com.application.domain.common.result.fold
import com.application.domain.common.useCase.NoParametersUseCase
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