package com.gig.hue.com.gig.hue.utilities.extensions

import android.util.Patterns
import com.gig.hue.com.gig.hue.enums.ValidateResult

fun String.validEmail(): ValidateResult = when {
        this.isEmpty() -> ValidateResult.EMPTY_STRING
        !Patterns.EMAIL_ADDRESS.matcher(this).matches() -> ValidateResult.INVALID_EMAIL
        else -> ValidateResult.VALID
    }


//Temporary
fun String.validPassword(): ValidateResult = when {
       this.isEmpty() -> ValidateResult.EMPTY_STRING
       this.length <= 6 -> ValidateResult.INVALID_PASSWORD
       else -> ValidateResult.VALID
   }


