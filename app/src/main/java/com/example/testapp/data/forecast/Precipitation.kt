package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class Precipitation(
    @Json(name = "max")
    val max: Double? = null,
    @Json(name = "min")
    val min: Double? = null,
    @Json(name = "pop")
    val pop: Int,
    @Json(name = "value")
    val value: Double
) : Parcelable