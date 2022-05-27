package com.gig.hue.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gig.hue.R
import com.gig.hue.databinding.ActivityLoginBinding
import com.gig.hue.view_models.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    //region BINDING
    private lateinit var binding: ActivityLoginBinding;
    //endregion

    //region VIEW MODEL
    private val viewModel: LoginViewModel by viewModels()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel;
        binding.lifecycleOwner = this;
    }
}





