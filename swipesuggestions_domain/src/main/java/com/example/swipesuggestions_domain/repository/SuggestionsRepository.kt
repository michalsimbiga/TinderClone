package com.example.swipesuggestions_domain.repository

import com.example.common_data.entity.Suggestion
import com.example.common_domain.result.Result

interface SuggestionsRepository {
    suspend fun getSuggestions(): Result<List<Suggestion>>
}