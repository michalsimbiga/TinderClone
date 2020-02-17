package com.example.swipesuggestions_domain

import com.example.common_domain.result.Result
import com.example.common_domain.useCase.NoParametersUseCase
import com.example.swipesuggestions_domain.entity.Suggestion
import com.example.swipesuggestions_domain.repository.SuggestionsRepository

class GetSuggestionListUseCase(private val repository: SuggestionsRepository) :
    NoParametersUseCase<List<Suggestion>>() {
    override suspend fun run(): Result<List<Suggestion>> = repository.getSuggestions()
}