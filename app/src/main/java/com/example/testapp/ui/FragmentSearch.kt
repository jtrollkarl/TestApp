package com.example.testapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_search_results.*

class FragmentSearch : Fragment() {

    companion object {
        const val TAG = "FragmentSearchResultsTag"
    }

    private lateinit var resultAdapter: AdapterSearchResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search_results, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_search_results.run {
            resultAdapter = AdapterSearchResult(emptyList()) { TODO("impl onclick logic") }
            val lm = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val decoration = DividerItemDecoration(requireContext(), lm.orientation)

            this.adapter = resultAdapter
            this.addItemDecoration(decoration)
            this.layoutManager = lm
        }

    }
}