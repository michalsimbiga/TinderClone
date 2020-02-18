package com.application.swipeSuggestions_ui.di

import com.example.common_domain.ModuleLoader
import com.example.common_remote.RemoteModules
import com.example.swipesuggestion_data.api.SuggestionsApi
import com.example.swipesuggestion_data.dataSource.SuggestionsRemoteDataSource
import com.example.swipesuggestion_data.repository.SuggestionsRepositoryImpl
import com.example.swipesuggestions_domain.GetSuggestionListUseCase
import com.example.swipesuggestions_domain.repository.SuggestionsRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

object SwipeSuggestionsModules : ModuleLoader() {
    override val modules: List<Module> =
        listOf(
            repositoryModule,
            remoteDataSource,
            apiModule,
            useCaseModule
        )

    override fun onLoad() {
        RemoteModules.load()
    }
}

private val repositoryModule = module {
    single<SuggestionsRepository> { SuggestionsRepositoryImpl(suggestionsRemoteDataSource = get()) }
}

private val remoteDataSource = module {
    single { SuggestionsRemoteDataSource(suggestionsApi = get()) }
}

private val apiModule = module {
    single<SuggestionsApi> { get<Retrofit>().create(SuggestionsApi::class.java) }
}

private val useCaseModule = module {
    factory { GetSuggestionListUseCase(repository = get()) }
}