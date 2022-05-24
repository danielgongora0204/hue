package com.gig.hue.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gig.hue.R
import com.gig.hue.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    //region VIEW BINDINGS
    private lateinit var binding: ActivityLoginBinding
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}





