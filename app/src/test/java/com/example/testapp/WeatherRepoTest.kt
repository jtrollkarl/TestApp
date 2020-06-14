package com.example.testapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testapp.api.WeatherApi
import com.example.testapp.data.forecast.ForecastResult
import com.example.testapp.data.searchresult.Location
import com.example.testapp.data.searchresult.SearchResult
import com.example.testapp.repo.WeatherRepo
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherRepoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var api: WeatherApi

    lateinit var subject: WeatherRepo

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        subject = WeatherRepo(api)
    }

    @Test
    fun whenGetForecaseDta_success(){
        runBlockingTest {
            val mockData = mockk<ForecastResult>(relaxed = true)
            coEvery { api.getForecast("TEST") } returns mockData
            subject.getForecastData("TEST")
            assert(subject.forecastResult.getValueForTest() == mockData)
        }
    }

    @Test
    fun whenGetWeatherData_success(){
        runBlockingTest {
            val mockData = mockk<SearchResult>(relaxed = true)
            val emptyList = emptyList<Location>()
            every { mockData.embedded?.location } returns emptyList

            coEvery { api.searchWeather("TEST", language = "en") } returns mockData
            subject.getWeatherDataLocation("TEST")
            assert(subject.weatherResult.getValueForTest() == emptyList)
        }
    }

}