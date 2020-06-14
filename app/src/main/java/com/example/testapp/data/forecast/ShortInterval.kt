package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class ShortInterval(
    @Json(name = "cloudCover")
    val cloudCover: CloudCover,
    @Json(name = "dewPoint")
    val dewPoint: DewPoint,
    @Json(name = "end")
    val end: String,
    @Json(name = "feelsLike")
    val feelsLike: FeelsLike,
    @Json(name = "humidity")
    val humidity: Humidity,
    @Json(name = "precipitation")
    val precipitation: Precipitation,
    @Json(name = "pressure")
    val pressure: Pressure,
    @Json(name = "start")
    val start: String,
    @Json(name = "symbol")
    val symbol: Symbol,
    @Json(name = "temperature")
    val temperature: Temperature,
    @Json(name = "uvIndex")
    val uvIndex: UvIndex,
    @Json(name = "wind")
    val wind: Wind
) : Parcelable {

    val startDate: String
        get() = start.substringBefore("T")

    val startTime: String
        get() = start.substringAfter("T").substringBefore("+")

    val endDate: String
        get() = end.substringBefore("T")

    val endTime: String
        get() = end.substringAfter("T").substringBefore("+")
}