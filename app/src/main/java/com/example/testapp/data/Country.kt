package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Country(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
): Parcelable