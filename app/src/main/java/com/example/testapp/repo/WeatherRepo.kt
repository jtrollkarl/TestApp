package com.example.testapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.api.WeatherApi
import com.example.testapp.data.forecast.ForecastResult
import com.example.testapp.data.forecast.LongInterval
import com.example.testapp.data.searchresult.Location
import com.example.testapp.data.searchresult.SearchResult
import timber.log.Timber
import javax.inject.Inject


class WeatherRepo @Inject constructor(private val api: WeatherApi) {

    private val _weatherResult = MutableLiveData<List<Location>>()
    val weatherResult: LiveData<List<Location>> = _weatherResult

    private val _forecastResult = MutableLiveData<ForecastResult>()
    val forecastResult: LiveData<ForecastResult> = _forecastResult

    suspend fun getWeatherDataLocation(location: String) {
        try {
            val result: SearchResult? = api.searchWeather(location = location, language = "en")
            result?.embedded?.location?.let {
                _weatherResult.postValue(it)
            } ?: Timber.e("Search results were null")
        } catch (e: Throwable) {
            Timber.e(e, "An error occurred")
        }
    }

    suspend fun getForecastData(id: String) {
        try {
            val result: ForecastResult? = api.getForecast(id)
            result?.let {
                _forecastResult.postValue(it)
            } ?: Timber.e("Forecast search results were null")
        } catch (e: Throwable) {
            Timber.e(e, "An error occurred")
        }
    }

}