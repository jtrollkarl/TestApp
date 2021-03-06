package com.example.testapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.searchresult.Location
import kotlinx.android.synthetic.main.list_item_search_result.view.*

class AdapterSearchResult(
    var locationList: List<Location> = emptyList(),
    val func: (Location) -> Unit
) : RecyclerView.Adapter<AdapterSearchResult.ViewHolderSearchResult>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchResult =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_search_result, parent, false).run {
                ViewHolderSearchResult(this)
            }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: ViewHolderSearchResult, position: Int) = holder.bind(locationList[position])

    inner class ViewHolderSearchResult(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(location: Location) {
            view.tv_city_country.text = view.context.getString(R.string.comma_city_country, location.name, location.country.name)
            view.setOnClickListener { func(location) }
        }
    }

}