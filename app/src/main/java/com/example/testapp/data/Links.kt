package com.example.testapp.data

data class Links(
    val airqualityforecast: Airqualityforecast,
    val bathingtemperatures: Bathingtemperatures,
    val celestialevents: Celestialevents,
    val extremeforecasts: Extremeforecasts,
    val forecast: Forecast,
    val mapfeature: Mapfeature,
    val notifications: Notifications,
    val now: Now,
    val observations: List<Observation>,
    val pollen: Pollen,
    val self: Self
)