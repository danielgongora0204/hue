package com.gig.hue.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gig.hue.R
import com.gig.hue.databinding.ActivityLoginBinding
import com.gig.hue.view_models.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    //region BINDING
    private lateinit var binding: ActivityLoginBinding
    //endregion

    //region VIEW MODEL
    private val viewModel: LoginViewModel by viewModels()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        collectViewModel()
    }

    private fun collectViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigateToMain.collect {
                        if (it) onLoginCompleted()
                    }
                }
                launch {
                    viewModel.password.collectLatest { viewModel.setPasswordErrorMessage(it) }
                }
                launch {
                    viewModel.username.collectLatest { viewModel.setUsernameErrorMessage(it) }
                }
            }
        }
    }

    private fun onLoginCompleted() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
        finish()
    }



}








