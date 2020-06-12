package com.example.testapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "category")
    val category: Category,
    @Json(name = "country")
    val country: Country,
    @Json(name = "elevation")
    val elevation: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "name")
    val name: String,
    @Json(name = "position")
    val position: Position,
    @Json(name = "region")
    val region: Region,
    @Json(name = "subregion")
    val subregion: Subregion,
    @Json(name = "timeZone")
    val timeZone: String,
    @Json(name = "urlPath")
    val urlPath: String
)