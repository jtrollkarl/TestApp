package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class CloudCover(
    @Json(name = "fog")
    val fog: Int,
    @Json(name = "high")
    val high: Int,
    @Json(name = "low")
    val low: Int,
    @Json(name = "middle")
    val middle: Int,
    @Json(name = "value")
    val value: Int
) : Parcelable