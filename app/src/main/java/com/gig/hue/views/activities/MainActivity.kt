package com.gig.hue.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gig.hue.R
import com.gig.hue.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //region BINDING
    private lateinit var binding: ActivityMainBinding
    //endregion

    //region NAVIGATION
    private lateinit var navController: NavController
    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        assignNavigation()
    }

    private fun assignNavigation(){
        with(binding) {
            navController = this.mainUnassignedNavHostContainer.getFragment<NavHostFragment>().navController
            mainUnassignedBottomNavigation.setupWithNavController(navController)
        }
    }
}