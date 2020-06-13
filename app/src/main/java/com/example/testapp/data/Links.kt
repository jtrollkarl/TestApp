package com.example.testapp.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Links(
    @Json(name = "airqualityforecast")
    val airqualityforecast: Airqualityforecast? = null,
    @Json(name = "bathingtemperatures")
    val bathingtemperatures: Bathingtemperatures? = null,
    @Json(name = "celestialevents")
    val celestialevents: Celestialevents? = null,
    @Json(name = "extremeforecasts")
    val extremeforecasts: Extremeforecasts? = null,
    @Json(name = "forecast")
    val forecast: Forecast,
    @Json(name = "mapfeature")
    val mapfeature: Mapfeature,
    @Json(name = "notifications")
    val notifications: Notifications? = null,
    @Json(name = "now")
    val now: Now? = null,
    @Transient
    @Json(name = "observations")
    val observations: List<Observation> = emptyList(),
    @Json(name = "pollen")
    val pollen: Pollen? = null,
    @Json(name = "self")
    val self: Self? = null
): Parcelable