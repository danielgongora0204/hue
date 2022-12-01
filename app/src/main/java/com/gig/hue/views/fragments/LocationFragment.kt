package com.gig.hue.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gig.hue.com.gig.hue.adapters.LocationAdapter
import com.gig.hue.com.gig.hue.view_models.LocationViewModel
import com.gig.hue.databinding.FragmentSearchBinding
import com.gig.hue.models.temp.RentItemTemp
import com.gig.hue.views.activities.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: LocationAdapter
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupUi()
        collectUi()
        return binding.root
    }

    private fun setupUi() {
        adapter = LocationAdapter { rentItemTemp ->
            showSnackBar(rentItemTemp as RentItemTemp)
        }
        with(binding){
            searchSwipeRefresh.apply {
                post { searchSwipeRefresh.isRefreshing = true }
                setOnRefreshListener(this@LocationFragment)
            }
            searchRecycler.adapter = adapter
        }
    }

    private fun collectUi() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.rentItems.collectLatest{
                    it?.let {
                        binding.searchSwipeRefresh.post { binding.searchSwipeRefresh.isRefreshing = false }
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                        if (it.isEmpty()) {
                            binding.searchNotFoundLayout.notFoundLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showSnackBar(rentItemTemp: RentItemTemp) =
        Snackbar.make(
            (requireActivity() as MainActivity).activityBinding.mainUnassignedCoordinatorLayout,
            "This worked: ${rentItemTemp.title}",
            Snackbar.LENGTH_SHORT
        ).show()


    override fun onRefresh() {
        viewModel.updateRentItems((1..140).random())
    }


}