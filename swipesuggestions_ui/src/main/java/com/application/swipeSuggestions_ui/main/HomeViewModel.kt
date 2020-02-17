package com.application.swipeSuggestions_ui.main

import com.airbnb.mvrx.*
import com.example.swipesuggestions_domain.GetSuggestionListUseCase
import com.example.swipesuggestions_domain.entity.Suggestion
import org.koin.android.ext.android.inject

data class HomeViewState(val suggestions: Async<List<Suggestion>> = Uninitialized) : MvRxState

class HomeViewModel (
    viewState: HomeViewState,
    private val getSuggestionListUseCase: GetSuggestionListUseCase
) : com.example.common_ui.MvRxViewModel<HomeViewState>(viewState) {


    init {
        fetchUsers()
    }

    fun fetchUsers() = getSuggestionListUseCase.execute(
        mapper = {it},
        stateReducer = { copy(suggestions = it)}
    )

    companion object : MvRxViewModelFactory<HomeViewModel, HomeViewState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: HomeViewState
        ): HomeViewModel {

            val getSuggestionListUseCase: GetSuggestionListUseCase by viewModelContext.activity.inject()

            return HomeViewModel(
                state,
                getSuggestionListUseCase
            )
        }
    }
}