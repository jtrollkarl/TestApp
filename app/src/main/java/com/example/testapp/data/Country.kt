package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
)