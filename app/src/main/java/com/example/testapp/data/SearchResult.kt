package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResult(
    @Json(name = "_embedded")
    val embedded: Embedded? = null,
    @Json(name = "_links")
    val links: LinksX,
    @Json(name = "totalResults")
    val totalResults: Int
): Parcelable