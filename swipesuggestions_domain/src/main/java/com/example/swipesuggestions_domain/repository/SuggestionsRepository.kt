package com.example.swipesuggestions_domain.repository

import com.example.common_domain.result.Result
import com.example.swipesuggestions_domain.entity.Suggestion

interface SuggestionsRepository {
    suspend fun getSuggestions(): Result<List<Suggestion>>
}