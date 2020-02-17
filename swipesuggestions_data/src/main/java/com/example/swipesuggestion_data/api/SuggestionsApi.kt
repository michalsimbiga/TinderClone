package com.example.swipesuggestion_data.api

import com.example.swipesuggestion_data.entity.Suggestion
import retrofit2.Response
import retrofit2.http.GET

interface SuggestionsApi {

    @GET("api/v1/suggestions/")
    suspend fun getSuggestions(): Response<List<Suggestion>>
}