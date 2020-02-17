package com.example.swipesuggestions_domain.entity

data class Suggestion(
    val id: Int,
    val name: String,
    val age: Int,
    val description: String,
    val listOfPhotos: List<PhotoFile>
)

data class PhotoFile(
    val id: Int,
    val uri: String
)