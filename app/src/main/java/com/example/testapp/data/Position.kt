package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Position(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double
): Parcelable