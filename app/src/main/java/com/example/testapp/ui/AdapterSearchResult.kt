package com.example.testapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.Location

class AdapterSearchResult(
    var locationList: List<Location> = emptyList(),
    val func: () -> Unit
) : RecyclerView.Adapter<AdapterSearchResult.ViewHolderSearchResult>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearchResult =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_iteam_search_result, parent, false).run {
                ViewHolderSearchResult(this)
            }

    override fun getItemCount(): Int = locationList.size

    override fun onBindViewHolder(holder: ViewHolderSearchResult, position: Int) {
        holder.bind(locationList[position])
    }

    class ViewHolderSearchResult(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(location: Location) {
            TODO()
        }
    }

}