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
import com.gig.hue.com.gig.hue.adapters.InboxAdapter
import com.gig.hue.com.gig.hue.view_models.InboxViewModel
import com.gig.hue.databinding.FragmentInboxBinding
import com.gig.hue.models.temp.ConversationItemTemp
import com.gig.hue.views.activities.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class InboxFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentInboxBinding
    private lateinit var adapter: InboxAdapter
    private val viewModel: InboxViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        setupUi()
        collectUi()
        return binding.root
    }

    private fun setupUi() {
        adapter = InboxAdapter { conversationItemTemp ->
            showSnackBar(conversationItemTemp as ConversationItemTemp)
        }
        with(binding) {
            inboxSwipeRefresh.isRefreshing = true
            inboxSwipeRefresh.setOnRefreshListener(this@InboxFragment)
            inboxRecyclerView.adapter = adapter
        }
    }

    private fun collectUi() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.conversationItems.collectLatest{
                    it?.let{
                        binding.inboxSwipeRefresh.isRefreshing = false
                        adapter.data = it
                        adapter.notifyDataSetChanged()
                        if (it.isEmpty()) {
                            binding.inboxNotFoundLayout.notFoundLayout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showSnackBar(conversationItemTemp: ConversationItemTemp) =
        Snackbar.make(
            (requireActivity() as MainActivity).activityBinding.mainUnassignedCoordinatorLayout,
            "This worked",
            Snackbar.LENGTH_SHORT
        ).show()


    override fun onRefresh() {
        viewModel.updateConversations((1..140).random())
    }

}