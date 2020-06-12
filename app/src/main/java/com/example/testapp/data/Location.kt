package com.example.testapp.data

data class Location(
    val _links: Links,
    val category: Category,
    val country: Country,
    val elevation: Int,
    val id: String,
    val name: String,
    val position: Position,
    val region: Region,
    val subregion: Subregion,
    val timeZone: String,
    val urlPath: String
)