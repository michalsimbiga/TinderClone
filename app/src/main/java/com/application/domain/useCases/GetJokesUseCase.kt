package com.application.domain.useCases

import com.application.domain.common.result.Result
import com.application.domain.common.useCase.NoParametersUseCase
import com.application.model.Joke
import com.application.model.JokeResponse
import com.application.repository.MyRepository
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(private val repository: MyRepository) :
    NoParametersUseCase<JokeResponse<List<Joke>>>() {
    override suspend fun run(): Result<JokeResponse<List<Joke>>> = repository.getJokes()
}