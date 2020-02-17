package com.example.swipesuggestion_data.dataSource

import com.example.common_domain.extensions.bodyOrException
import com.example.common_domain.result.safeCall
import com.example.swipesuggestion_data.api.SuggestionsApi

class SuggestionsRemoteDataSource(private val suggestionsApi: SuggestionsApi) {

    suspend fun getSuggestions() = safeCall {
        suggestionsApi.getSuggestions()
            .bodyOrException()
    }
}