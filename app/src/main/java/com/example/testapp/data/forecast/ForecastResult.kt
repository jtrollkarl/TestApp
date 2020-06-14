package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import timber.log.Timber
import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class ForecastResult(
    @Json(name = "created")
    val created: String,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "longIntervals")
    val longIntervals: List<LongInterval>,
    @Json(name = "shortIntervals")
    val shortIntervals: List<ShortInterval>,
    @Json(name = "update")
    val update: String
) : Parcelable {

    @SuppressLint("SimpleDateFormat")
    private fun getDateFormat() = SimpleDateFormat("yyyy-MM-dd")

    //"2020-06-13T20:00:00+02:00"
    private fun getCurrentTimeString(): String = currentDateString.substringAfter("T").substringBefore("+")

    val currentDateString: String
        get() {
            val date = Calendar.getInstance().time
            return getDateFormat().format(date)
        }

    val listOfStartTimes: List<Date>
        get() = shortIntervals.map { getDateFormat().parse(it.start.substringBefore("T")) }

    val listOfEndTimes: List<Date>
        get() = shortIntervals.map { getDateFormat().parse(it.end.substringBefore("T")) }

    val currentTemperature: Int
        get() = shortIntervals[0].temperature.value.toInt()

    val currentDateApi: String
        get() = shortIntervals[0].startDate

    val currentDayHigh: Int
        get() = getHighForDay(shortIntervals[0].startDate)

    val currentDayLow: Int
        get() = getLowForDay(shortIntervals[0].startDate)

    fun getCurrentWeatherIcon(c: Context): Drawable? = shortIntervals[0].symbol.getSymbolDrawable(c)

    //combine the following 2 functions
    private fun getHighForDay(date: String): Int =
        shortIntervals.filter { it.startDate == date }.map { it.temperature.value }.max()?.toInt() ?: 0

    private fun getLowForDay(date: String): Int =
        shortIntervals.filter { it.startDate == date }.map { it.temperature.value }.min()?.toInt() ?: 0

    //this could probably be optimized
    private fun getHighsLows(daysAfter: Int): List<TempHighLow> {
        val list = ArrayList<TempHighLow>()
        shortIntervals.map { it.startDate }.distinct().subList(0, daysAfter)
            .forEach { list.add(TempHighLow(it, getLowForDay(it), getHighForDay(it))) }
        return list
    }

    //to get a list of the general weather icons of the upcoming days
    private fun getSymbolsMidDayAfter(daysAfter: Int): List<Symbol> {
        return longIntervals.filter { it.timeInRange("12:00:00")}.map { it.symbol }.subList(0, daysAfter)
    }

    fun getWeatherDaysAfter(): List<BasicWeather>{
        var daysAfter = 4

        val results = ArrayList<BasicWeather>()
        var listHighsLows: List<TempHighLow>
        var listSymbols: List<Symbol>

        try {
            listHighsLows = getHighsLows(daysAfter)
            listSymbols = getSymbolsMidDayAfter(daysAfter)
        } catch (e: IndexOutOfBoundsException){
            Timber.w(e,"Index was out of bounds, can only show 3 dates")
            daysAfter = 3
            listHighsLows = getHighsLows(daysAfter)
            listSymbols = getSymbolsMidDayAfter(daysAfter)
        }

        for (i in 0 until daysAfter){
            val date = listHighsLows[i].date
            val low = listHighsLows[i].low
            val high = listHighsLows[i].high
            val symbol = listSymbols[i]
            results.add(BasicWeather(date, low, high, symbol))
        }
        results.removeAt(0)
        return results
    }

    private data class TempHighLow(val date: String, val low: Int, val high: Int)

    data class BasicWeather(val date: String, val low: Int, val high: Int, val symbol: Symbol)
}