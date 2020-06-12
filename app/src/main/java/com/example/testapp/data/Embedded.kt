package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Embedded(
    @Json(name = "location")
    val location: List<Location>
)