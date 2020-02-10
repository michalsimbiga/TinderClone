package com.application.domain.useCases

import com.application.data.Suggestion
import com.application.domain.common.result.Result
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.repository.MyRepository
import javax.inject.Inject

class GetSuggestionList @Inject constructor(private val repository: MyRepository) :
    NoParametersUseCase<List<Suggestion>>() {
    override suspend fun run(): Result<List<Suggestion>> = repository.getUsers()
}