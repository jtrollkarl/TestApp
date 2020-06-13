package com.example.testapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.api.WeatherApi
import com.example.testapp.data.Location
import com.example.testapp.data.SearchResult
import timber.log.Timber
import javax.inject.Inject


class WeatherRepo @Inject constructor(private val api: WeatherApi) {

    private val _weatherResult = MutableLiveData<List<Location>>()

    val weatherResult: LiveData<List<Location>> = _weatherResult

    suspend fun getWeatherDataLocation(location: String){
        try {
            val result: SearchResult = api.searchWeather(location = location, language = "en")
            _weatherResult.postValue(result.embedded.location)
        } catch (e: Throwable){
            Timber.e(e, "An error occurred")
        }

    }

}