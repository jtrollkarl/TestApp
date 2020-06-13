package com.example.testapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ViewModelFactory
import com.example.testapp.data.Location
import com.example.testapp.showToast
import com.example.testapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*
import timber.log.Timber
import javax.inject.Inject

class FragmentSearch : Fragment() {

    companion object {
        const val TAG = "FragmentSearchResultsTag"
    }

    private lateinit var resultAdapter: AdapterSearchResult
    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search_results, container, false)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search_fragment, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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
        viewModel = ViewModelProvider(this, vmFactory).get(WeatherViewModel::class.java)

        recycler_search_results.run {
            resultAdapter = AdapterSearchResult(emptyList()) { TODO("impl onclick logic") }
            val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val decoration = DividerItemDecoration(requireContext(), lm.orientation)

            this.adapter = resultAdapter
            this.addItemDecoration(decoration)
            this.layoutManager = lm
        }

        viewModel.liveDataSearchResult.observe(viewLifecycleOwner, Observer<List<Location>> {
            resultAdapter.locationList = it
            Timber.d(it[0].name)
        })
    }


}