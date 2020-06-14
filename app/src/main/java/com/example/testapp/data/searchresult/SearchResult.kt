package com.example.testapp.data.searchresult


import android.os.Parcelable
import com.example.testapp.data.searchresult.Embedded
import com.example.testapp.data.searchresult.LinksX
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