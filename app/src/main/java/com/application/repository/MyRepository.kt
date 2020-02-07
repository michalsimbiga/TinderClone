package com.application.repository

import com.application.domain.common.extensions.bodyOrException
import com.application.domain.common.result.Result
import com.application.domain.common.result.safeCall
import com.application.model.Joke
import com.application.model.JokeResponse
import com.application.net.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(private val restApi: RestApi) : BaseRepository() {

    suspend fun getJokes(): Result<JokeResponse<List<Joke>>> =
        safeCall {
            restApi.getJokes()
                .bodyOrException()
        }
}