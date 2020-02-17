package com.application.swipeSuggestions_ui.di

import com.example.common_domain.ModuleLoader
import com.example.swipesuggestion_data.api.SuggestionsApi
import com.example.swipesuggestion_data.dataSource.SuggestionsRemoteDataSource
import com.example.swipesuggestion_data.repository.SuggestionsRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

object SwipeSuggestionsModules : ModuleLoader() {
    override val modules: List<Module> = listOf(repositoryModule)

}


private val repositoryModule = module {
    single { SuggestionsRepositoryImpl(suggestionsRemoteDataSource = get()) }
}

private val remoteDataSource = module {
    single { SuggestionsRemoteDataSource(suggestionsApi = get())}
}

private val apiModule = module {
    single<SuggestionsApi> { get<Retrofit>().create(SuggestionsApi::class.java)}
}