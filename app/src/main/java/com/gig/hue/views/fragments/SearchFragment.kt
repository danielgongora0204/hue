package com.gig.hue.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gig.hue.R
import com.gig.hue.com.gig.hue.adapters.SearchLocationAdapter
import com.gig.hue.databinding.FragmentSearchBinding
import com.gig.hue.models.temp.RentItemTemp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchLocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        collectUi()
        return binding.root
    }

    private fun collectUi() {
        with(binding) {
            if(searchRecycler.adapter == null) {
                adapter = SearchLocationAdapter().apply {
                    data = getTempData()
                }
                searchRecycler.adapter = adapter
                return
            }
            adapter.data = getTempData()
            adapter.notifyDataSetChanged()
        }
    }

    //TODO: THIS IS A TEMP METHOD TO BE USED AS A DATA ORIGIN WHILE DEV VIEW MODEL
    private fun getTempData(): List<RentItemTemp>
    = listOf(
        RentItemTemp(
            "Hello",
            "This is the first tittle",
            "This is the first secondary text",
            "This is the first paragraph"
        ),
        RentItemTemp(
            "Hello",
            "This is the second tittle",
            "This is the second secondary text",
            "This is the second paragraph"
        ),
        RentItemTemp(
            "Hello",
            "This is the third tittle",
            "This is the third secondary text",
            "This is the third paragraph"
        )
    )
}