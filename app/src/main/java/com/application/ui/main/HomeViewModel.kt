package com.application.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import com.application.domain.useCases.GetJokesUseCase
import com.application.model.Joke
import com.application.ui.base.MvRxViewModel
import com.application.vm.AssistedViewModelFactory
import com.application.vm.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class HomeState(@PersistState val jokes: Async<List<Joke>> = Uninitialized) : MvRxState

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val getJokesUseCase: GetJokesUseCase
) : MvRxViewModel<HomeState>(state) {


    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    fun fetchJokes() =
        getJokesUseCase.execute(
            mapper = { it.value },
            stateReducer = { copy(jokes = it) }
        )

    companion object :
        DaggerMvRxViewModelFactory<HomeViewModel, HomeState>(HomeViewModel::class.java)
}