package com.example.testapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.testapp.repo.WeatherRepo
import com.example.testapp.viewmodel.WeatherViewModel
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @RelaxedMockK
    lateinit var repo: WeatherRepo

    private lateinit var subject: WeatherViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        subject = WeatherViewModel(repo)
    }

    @Test
    fun whenSearchWeather_doApiCall() {
        subject.searchWeather("test")
        coVerify { repo.getWeatherDataLocation("test") }
    }

    @Test
    fun whenGetForecast_doApiCall() {
        subject.getForecast("test")
        coVerify { repo.getForecastData("test") }
    }

    @Test
    fun whenSelectedLocation_updateLocation() {
        subject.setSelectedLocation("Toronto")
        assert(subject.liveDataSelectedLocation.getValueForTest() == "Toronto")
    }
}