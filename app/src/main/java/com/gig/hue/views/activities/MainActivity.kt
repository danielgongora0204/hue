package com.gig.hue.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gig.hue.R
import com.gig.hue.view_models.MainActivityViewModel
import com.gig.hue.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //region BINDING
    private var binding: ActivityMainBinding? = null
    val activityBinding get() = binding!!
    //endregion

    //region VIEW MODEL
    private val viewModel: MainActivityViewModel by viewModels()
    //endregion

    //region NAVIGATION
    private lateinit var navController: NavController
    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        assignNavigation()
        collectViewModel()
    }

    private fun assignNavigation(){
        binding?.let{
            navController = it.mainUnassignedNavHostContainer.getFragment<NavHostFragment>().navController
            it.mainUnassignedBottomNavigation.setupWithNavController(navController)
        }
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            viewModel.currentFragment.collect {
                it?.let {
                    NavigationUI.onNavDestinationSelected(it, navController)
                    binding?.mainUnassignedAppbarLayout?.setExpanded(true)
                }
            }
        }
    }


}