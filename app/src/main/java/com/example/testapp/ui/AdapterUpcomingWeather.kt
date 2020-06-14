package com.example.testapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.forecast.ForecastResult
import kotlinx.android.synthetic.main.list_item_weather_day.view.*


class AdapterUpcomingWeather(var weatherList: List<ForecastResult.BasicWeather>): RecyclerView.Adapter<AdapterUpcomingWeather.ViewHolderUpcomingWeather>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUpcomingWeather =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_weather_day, parent, false).run {
                ViewHolderUpcomingWeather(this)
            }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolderUpcomingWeather, position: Int) = holder.bind(weatherList[position])

    inner class ViewHolderUpcomingWeather(private val v: View): RecyclerView.ViewHolder(v){
        fun bind(weather: ForecastResult.BasicWeather){
            v.tv_date.text = weather.date
            v.tv_low_temp.text = weather.low.toString()
            v.tv_high_temp.text = weather.high.toString()
            weather.symbol.getSymbolDrawable(v.context)?.let {
                v.iv_weather.setImageDrawable(it)
            }
        }
    }
}