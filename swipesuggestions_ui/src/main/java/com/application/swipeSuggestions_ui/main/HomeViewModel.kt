package com.application.swipeSuggestions_ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.application.common_data.Suggestion
import com.application.swipeSuggestions_domain.useCases.GetJokesUseCase
import com.application.swipeSuggestions_domain.useCases.GetSuggestionList
import com.example.common_ui.MvRxViewModel
import com.example.common_domain.AssistedViewModelFactory
import com.example.common_domain.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

data class HomeState(val suggestions: Async<List<Suggestion>> = Uninitialized) : MvRxState

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val getJokesUseCase: GetJokesUseCase,
    private val getSuggestionList: GetSuggestionList
) : com.example.common_ui.MvRxViewModel<HomeState>(state) {

    @AssistedInject.Factory
    interface Factory :
        AssistedViewModelFactory<HomeViewModel, HomeState> {
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