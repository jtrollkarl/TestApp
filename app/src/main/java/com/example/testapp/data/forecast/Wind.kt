package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "direction")
    val direction: Int? = null,
    @Json(name = "gust")
    val gust: Double? = null,
    @Json(name = "speed")
    val speed: Double? = null
) : Parcelable