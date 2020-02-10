package com.application.repository

import com.application.data.PhotoFile
import com.application.data.Suggestion
import com.application.domain.common.extensions.bodyOrException
import com.application.domain.common.result.Result
import com.application.domain.common.result.safeCall
import com.application.domain.common.result.success
import com.application.model.Joke
import com.application.model.JokeResponse
import com.application.net.RestApi
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(private val restApi: RestApi) {

    suspend fun getJokes(): Result<JokeResponse<List<Joke>>> =
        safeCall {
            restApi.getJokes()
                .bodyOrException()
        }

    suspend fun getUsers() = safeCall {
        delay(400)
        mockedUsers }

    private val violettaPhotos = listOf(
        PhotoFile(1, "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"),
        PhotoFile(2, "https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"),
        PhotoFile(3, "https://images.unsplash.com/photo-1515191107209-c28698631303?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60")
    )

    private val mockedUsers: List<Suggestion> =
        listOf(
            Suggestion(
                id = 1,
                name = "Violetta Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(violettaPhotos[0])
            ),
            Suggestion(
                id = 2,
                name = "Justyna Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(violettaPhotos[1])
            ),
            Suggestion(
                id = 3,
                name = "Esmeralda Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(violettaPhotos[2])
            )
        )

}