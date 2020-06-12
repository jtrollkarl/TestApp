package com.example.testapp.api

import com.example.testapp.data.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/locations/Search")
    suspend fun searchWeather(@Query("q") location: String,
                              @Query("lat") latitude: Double,
                              @Query("lon") longitude: Double,
                              @Query("accuracy") accuracy: Double,
                              @Query("language") language: String): SearchResult



}