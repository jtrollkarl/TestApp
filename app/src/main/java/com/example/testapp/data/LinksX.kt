package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksX(
    @Json(name = "location")
    val location: List<LocationX>,
    @Json(name = "page")
    val page: Page,
    @Json(name = "search")
    val search: Search,
    @Json(name = "self")
    val self: SelfX
)