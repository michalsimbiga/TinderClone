package com.application.repository

import com.application.model.Joke
import com.application.net.Response
import com.application.net.RestApi
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MyRepository @Inject constructor(private val restApi: RestApi) : BaseRepository() {

    suspend fun getJokes(): Response<List<Joke>> {
        val result = call { restApi.getJokes() }

        return if (result is Response.Success) {
            Response.Success(result.data.value)
        } else result as Response<List<Joke>>
    }
}