package com.gig.hue.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {
    //region Variables
    private var auth = Firebase.auth;

    //endregion

    //region PublicMethods
    public suspend fun athenticationCheck(): Boolean = auth.currentUser != null

    //endregion
}