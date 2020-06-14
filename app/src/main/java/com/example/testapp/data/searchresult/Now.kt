package com.example.testapp.data.searchresult


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Now(
    @Json(name = "href")
    val href: String
): Parcelable