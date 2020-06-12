package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Position(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double
)