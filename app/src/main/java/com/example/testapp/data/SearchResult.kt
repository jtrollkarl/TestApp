package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "_embedded")
    val embedded: Embedded,
    @Json(name = "_links")
    val links: LinksX,
    @Json(name = "totalResults")
    val totalResults: Int
)