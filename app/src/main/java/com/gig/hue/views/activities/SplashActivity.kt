package com.gig.hue.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gig.hue.view_models.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectViewModel()
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            viewModel.isUserLoggedIn.collect {
                val intent = if (it) Intent(this@SplashActivity, LoginActivity::class.java)
                else Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}