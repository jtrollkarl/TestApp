package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bathingtemperatures(
    @Json(name = "href")
    val href: String
)