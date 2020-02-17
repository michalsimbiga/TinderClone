package com.example.swipesuggestion_data.repository

import com.example.common_data.entity.PhotoFile
import com.example.common_data.entity.Suggestion
import com.example.common_domain.result.Result
import com.example.common_domain.result.safeCall
import com.example.swipesuggestion_data.dataSource.SuggestionsRemoteDataSource
import com.example.swipesuggestions_domain.repository.SuggestionsRepository
import kotlinx.coroutines.delay

class SuggestionsRepositoryImpl(private val suggestionsRemoteDataSource: SuggestionsRemoteDataSource):
    SuggestionsRepository {

    override suspend fun getSuggestions(): Result<List<Suggestion>> = safeCall {
        delay(400)
        mockedUsers }
    }

    private val violettaPhotos = listOf(
        PhotoFile(
            1,
            "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
        ),
        PhotoFile(
            2,
            "https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
        ),
        PhotoFile(
            3,
            "https://images.unsplash.com/photo-1515191107209-c28698631303?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
        ),
        PhotoFile(
            4,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQOwwM9073_bg7yrDmQG8xJQFv11eHfDMCQGXdjh6Xx4WR9G4U4"
        ),
        PhotoFile(
            5,
            "https://cdn.vox-cdn.com/thumbor/Oi6jzvQzWetJGlkpwLqlP1L9p28=/0x0:5568x3712/1200x800/filters:focal(2858x720:3748x1610)/cdn.vox-cdn.com/uploads/chorus_image/image/62207705/922984782.jpg.0.jpg"
        ),
        PhotoFile(
            6,
            "https://cdn.cnn.com/cnnnext/dam/assets/181003102639-marc-benioff-exlarge-169.jpg"
        ),
        PhotoFile(
            7,
            "https://www.aconsciousrethink.com/wp-content/uploads/2019/11/put-others-down-702x336.jpg"
        ),
        PhotoFile(
            8,
            "https://peopledotcom.files.wordpress.com/2019/12/wp-tout.jpg?crop=0px%2C0px%2C1920px%2C1008px&resize=1200%2C630"
        ),
        PhotoFile(
            9,
            "https://cdn.lifehack.org/wp-content/uploads/2015/02/what-makes-people-happy.jpeg"
        ),
        PhotoFile(
            10,
            "https://images.ladbible.com/thumbnail?type=jpeg&url=http://beta.ems.ladbiblegroup.com/s3/content/a0a6fd945d910d10cf48bfc3c7fb3fc1.png&quality=70&width=720"
        ),
        PhotoFile(
            11,
            "https://www.tubefilter.com/wp-content/uploads/2019/11/dobrik-people.jpg"
        ),
        PhotoFile(
            12,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBs8QrmT45OfILtIiOyljerM6qagQfYN5bN4CRrZeG3VI97NbO"
        )
    )

    private val mockedUsers: List<Suggestion> =
        listOf(
            Suggestion(
                id = 1,
                name = "Violetta Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(
                    violettaPhotos[0],
                    violettaPhotos[1],
                    violettaPhotos[2],
                    violettaPhotos[3],
                    violettaPhotos[4]
                )
            ),
            Suggestion(
                id = 2,
                name = "Justyna Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(
                    violettaPhotos[5],
                    violettaPhotos[6],
                    violettaPhotos[7],
                    violettaPhotos[8],
                    violettaPhotos[9]
                )
            ),
            Suggestion(
                id = 3,
                name = "Esmeralda Villas",
                description = "Just a normal girl looking for a great adventure",
                age = 30,
                listOfPhotos = listOf(violettaPhotos[10], violettaPhotos[11])
            )
        )
}