package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Links(
    @Json(name = "airqualityforecast")
    val airqualityforecast: Airqualityforecast,
    @Json(name = "bathingtemperatures")
    val bathingtemperatures: Bathingtemperatures,
    @Json(name = "celestialevents")
    val celestialevents: Celestialevents,
    @Json(name = "extremeforecasts")
    val extremeforecasts: Extremeforecasts,
    @Json(name = "forecast")
    val forecast: Forecast,
    @Json(name = "mapfeature")
    val mapfeature: Mapfeature,
    @Json(name = "notifications")
    val notifications: Notifications,
    @Json(name = "now")
    val now: Now,
    @Json(name = "observations")
    val observations: List<Observation>,
    @Json(name = "pollen")
    val pollen: Pollen,
    @Json(name = "self")
    val self: Self
): Parcelable