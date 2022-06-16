package com.gig.hue.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gig.hue.com.gig.hue.data.repositories.SplashRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(splashRepository: SplashRepository) : ViewModel() {
    //region Variables
    private var auth = Firebase.auth;

    val isUserLoggedIn = splashRepository.isUserLoggedIn().asLiveData()
    //endregion

}