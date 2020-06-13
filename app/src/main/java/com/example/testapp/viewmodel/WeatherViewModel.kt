package com.example.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.Location
import com.example.testapp.repo.WeatherRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val repo: WeatherRepo) : ViewModel() {

    val liveDataSearchResult: LiveData<List<Location>> = repo.weatherResult

    fun searchWeather(location: String?) {
        location?.let {
            doSuspendWork { repo.getWeatherDataLocation(it) }
        } ?: Timber.e("location string was null")
    }

    private fun doSuspendWork(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            block()
        }
    }

}