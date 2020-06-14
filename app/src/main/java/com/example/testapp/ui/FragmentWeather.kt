package com.example.testapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ViewModelFactory
import com.example.testapp.data.forecast.ForecastResult
import com.example.testapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import javax.inject.Inject

class FragmentWeather : Fragment() {

    companion object {
        const val TAG = "FragmentWeatherTag"
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: WeatherViewModel

    private lateinit var weatherAdapter: AdapterUpcomingWeather

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search_fragment, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchWeather(query)
                searchItem.collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.getApp(requireActivity()).component.inject(this)

        viewModel = ViewModelProvider(requireActivity(), vmFactory)[WeatherViewModel::class.java]

        viewModel.liveDataSelectedLocation.observe(viewLifecycleOwner, Observer {
            tv_location.text = it
        })

        viewModel.liveDataForecast.observe(viewLifecycleOwner, Observer {
            tv_current_temp.text = getString(R.string.degrees_c, it.currentTemperature.toString())
            tv_date.text = it.currentDateApi
            tv_high_temp.text = getString(R.string.degrees_c, it.currentDayHigh.toString())
            tv_low_temp.text = getString(R.string.degrees_c, it.currentDayLow.toString())
            it.getCurrentWeatherIcon(requireActivity())?.let { drawable ->
                iv_icon.setImageDrawable(drawable)
            }
            val basicWeather: List<ForecastResult.BasicWeather> = it.getWeatherDaysAfter(3)

            recycler_forecast.run {
                weatherAdapter = AdapterUpcomingWeather(basicWeather)

                val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                val decoration = DividerItemDecoration(requireContext(), lm.orientation)

                this.adapter = weatherAdapter
                this.addItemDecoration(decoration)
                this.layoutManager = lm
            }
        })
    }
}