package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class LongInterval(
    @Json(name = "cloudCover")
    val cloudCover: CloudCover? = null,
    @Json(name = "dewPoint")
    val dewPoint: DewPoint,
    @Json(name = "end")
    val end: String,
    @Json(name = "feelsLike")
    val feelsLike: FeelsLike,
    @Json(name = "humidity")
    val humidity: Humidity? = null,
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
    val uvIndex: UvIndex? = null,
    @Json(name = "wind")
    val wind: Wind? = null
) : Parcelable {

    @SuppressLint("SimpleDateFormat")
    @IgnoredOnParcel
    val format = SimpleDateFormat("HH:mm:ss")

    val startDate: String
        get() = start.substringBefore("T")

    val startTime: String
        get() = start.substringAfter("T").substring(0, 8)

    val endDate: String
        get() = end.substringBefore("T")

    val endTime: String
        get() = end.substringAfter("T").substring(0, 8)

    fun timeInRange(time: String): Boolean {
        val start: Date? = format.parse(startTime)
        val end: Date? = format.parse(endTime)

        val desired: Date? = format.parse(time)

        return (start?.before(desired) ?: true && end?.after(desired) ?: false)
    }

}