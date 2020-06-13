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
import javax.inject.Inject

class FragmentSearchResults : Fragment() {

    private lateinit var resultAdapter: AdapterSearchResult
    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var vmFactory: ViewModelFactory

    companion object {
        private const val RESULTS_KEY = "results_key"
        const val TAG = "FragmentSearchResultsTag"

        fun newInstance(results: List<Location>): FragmentSearchResults =
            FragmentSearchResults().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(RESULTS_KEY, ArrayList(results))
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_results, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.getApp(requireActivity()).component.inject(this)
        viewModel = ViewModelProvider(requireActivity(), vmFactory).get(WeatherViewModel::class.java)

        val results: List<Location> = arguments?.getParcelableArrayList(RESULTS_KEY) ?: emptyList()

        recycler_search_results.run {
            resultAdapter = AdapterSearchResult(results) { showToast(it.name) }
            val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val decoration = DividerItemDecoration(requireContext(), lm.orientation)

            this.adapter = resultAdapter
            this.addItemDecoration(decoration)
            this.layoutManager = lm
        }

    }


}