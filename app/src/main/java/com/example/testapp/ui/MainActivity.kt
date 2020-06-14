package com.example.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ViewModelFactory
import com.example.testapp.data.forecast.ForecastResult
import com.example.testapp.data.forecast.LongInterval
import com.example.testapp.data.searchresult.Location
import com.example.testapp.doTransaction
import com.example.testapp.viewmodel.WeatherViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getApp(this).component.inject(this)
        setContentView(R.layout.activity_main)
        showWeatherFragmentIfStackEmpty()

        val viewModel = ViewModelProvider(this, vmFactory)[WeatherViewModel::class.java]

        viewModel.liveDataSearchResult.observe(this, Observer {
            showResultsFragment(it)
        })
        viewModel.liveDataForecast.observe(this, Observer { showForecastResults(it) })
    }

    private fun showWeatherFragmentIfStackEmpty() {
        supportFragmentManager.takeIf { it.fragments.isEmpty() }
            ?.doTransaction { replace(R.id.container, FragmentWeather(), FragmentWeather.TAG) }
    }

    private fun showForecastResults(result: ForecastResult){
        supportFragmentManager.popBackStack()
        //val fragmentWeather: FragmentWeather = supportFragmentManager.findFragmentByTag(FragmentWeather.TAG) as FragmentWeather
    }

    private fun showResultsFragment(list: List<Location>) {
        val fragment = FragmentSearchResults.newInstance(list)
        supportFragmentManager.doTransaction {
            replace(
                R.id.container,
                fragment,
                FragmentSearchResults.TAG
            ).addToBackStack(null)
        }
    }

}
