package com.example.swipesuggestions_domain

import com.example.common_data.entity.Suggestion
import com.example.common_domain.result.Result
import com.example.common_domain.useCase.NoParametersUseCase
import com.example.swipesuggestions_domain.repository.SuggestionsRepository

class GetSuggestionList(private val repository: SuggestionsRepository) :
    NoParametersUseCase<List<Suggestion>>() {
    override suspend fun run(): Result<List<Suggestion>> = repository.getSuggestions()
}