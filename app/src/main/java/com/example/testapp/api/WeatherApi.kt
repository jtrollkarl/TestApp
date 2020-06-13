package com.example.testapp.api

import com.example.testapp.data.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("locations/Search")
    suspend fun searchWeather(@Query("q") location: String? = null,
                              @Query("lat") latitude: Double? = null,
                              @Query("lon") longitude: Double? = null,
                              @Query("accuracy") accuracy: Double? = null,
                              @Query("language") language: String): SearchResult



}