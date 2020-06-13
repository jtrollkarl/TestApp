package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class LinksX(
    @Json(name = "location")
    val location: List<LocationX>? = null,
    @Json(name = "page")
    val page: Page,
    @Json(name = "search")
    val search: Search,
    @Json(name = "self")
    val self: SelfX
): Parcelable