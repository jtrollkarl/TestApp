package com.example.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.forecast.ForecastResult
import com.example.testapp.data.searchresult.Location
import com.example.testapp.repo.WeatherRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val repo: WeatherRepo) : ViewModel() {

    val liveDataSearchResult: LiveData<List<Location>> = repo.weatherResult
    val liveDataForecast: LiveData<ForecastResult> = repo.forecastResult

    private val _liveDataSelectedLocation = MutableLiveData<String>()
    val liveDataSelectedLocation: LiveData<String> = _liveDataSelectedLocation

    fun searchWeather(location: String?) {
        location?.let {
            doSuspendWork { repo.getWeatherDataLocation(it) }
        } ?: Timber.e("location string was null")
    }

    fun getForecast(id: String) {
        doSuspendWork { repo.getForecastData(id) }
    }

    private fun doSuspendWork(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            block()
        }
    }

    fun setSelectedLocation(location: String) {
        _liveDataSelectedLocation.value = location
    }

}