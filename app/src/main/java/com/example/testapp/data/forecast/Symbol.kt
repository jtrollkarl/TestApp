package com.example.testapp.data.forecast


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class Symbol(
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "n")
    val n: Int,
    @Json(name = "precip")
    val precip: Int,
    @Json(name = "sunup")
    val sunup: Boolean,
    @Json(name = "var")
    val varX: String? = null
) : Parcelable {

    private fun getSymbolVar(): String {
        return when (varX) {
            "Sun" -> "d"
            "Moon" -> "n"
            "Polar" -> "m"
            null -> ""
            else -> ""
        }
    }

    private fun getSymbolResString(): String = "ic_${n}${getSymbolVar()}"

    fun getSymbolDrawable(c: Context): Drawable? {
        val resId: Int = c.resources.getIdentifier(getSymbolResString(), "drawable", c.packageName)
        return ContextCompat.getDrawable(c, resId)
    }

}