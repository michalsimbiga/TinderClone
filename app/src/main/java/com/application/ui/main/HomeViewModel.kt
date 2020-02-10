package com.application.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import com.application.data.Suggestion
import com.application.domain.useCases.GetJokesUseCase
import com.application.domain.useCases.GetSuggestionList
import com.application.model.Joke
import com.application.ui.base.MvRxViewModel
import com.application.vm.AssistedViewModelFactory
import com.application.vm.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class HomeState(val suggestions: Async<List<Suggestion>> = Uninitialized) : MvRxState

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val getJokesUseCase: GetJokesUseCase,
    private val getSuggestionList: GetSuggestionList
) : MvRxViewModel<HomeState>(state) {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    init {
        fetchUsers()
    }

//    fun fetchJokes() =
//        getJokesUseCase.execute(
//            mapper = { it.value },
//            stateReducer = { copy(jokes = it) }
//        )

    fun fetchUsers() = getSuggestionList.execute(
        mapper = {it},
        stateReducer = { copy(suggestions = it)}
    )

    companion object :
        DaggerMvRxViewModelFactory<HomeViewModel, HomeState>(HomeViewModel::class.java)
}