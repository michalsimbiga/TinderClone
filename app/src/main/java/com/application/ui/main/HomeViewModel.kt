package com.application.ui.main

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.*
import com.application.MvRxViewModel
import com.application.model.Joke
import com.application.net.Response
import com.application.repository.MyRepository
import com.application.vm.AssistedViewModelFactory
import com.application.vm.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber

data class HomeState(val jokes: Async<List<Joke>> = Uninitialized) : MvRxState

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val repository: MyRepository
) : MvRxViewModel<HomeState>(state) {

    init {
        fetchJokes()
    }

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    fun fetchJokes() = withState { state: HomeState ->
        viewModelScope.launch {
            val response = repository.getJokes()
            Timber.i("TESTING state jokes ${state.jokes}")
            Timber.i("TESTING response $response")
            if (response is Response.Success) setState { state.copy(jokes = Success(response.data)) }
        }
    }

    companion object :
        DaggerMvRxViewModelFactory<HomeViewModel, HomeState>(HomeViewModel::class.java)
}