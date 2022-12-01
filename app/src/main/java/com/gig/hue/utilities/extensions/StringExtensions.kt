package com.gig.hue.utilities.extensions

import android.util.Patterns
import com.gig.hue.enums.ValidateResult

/*
fun String.validEmail(): ValidateResult = when {
        this.isEmpty() -> ValidateResult.EMPTY_STRING
        !Patterns.EMAIL_ADDRESS.matcher(this).matches() -> ValidateResult.INVALID_EMAIL
        else -> ValidateResult.VALID
    }
*/

//TODO: Temporary
/*fun String.validPassword(): ValidateResult = when {
       this.isEmpty() -> ValidateResult.EMPTY_STRING
       this.length <= 6 -> ValidateResult.INVALID_PASSWORD
       else -> ValidateResult.VALID
   }*/

fun String?.checkPassword(): String? = when {
    this.default("").length <= 6 -> this
    this == "" -> this
    else -> null
}

fun String?.checkUsername(): String? = when {
    !Patterns.EMAIL_ADDRESS.matcher(this.default("")).matches() -> this
    this == "" -> this
    else -> null
}

fun String?.getCheckResult(validateResult: ValidateResult): ValidateResult = when {
    this == "" -> ValidateResult.EMPTY_STRING
    this == null -> ValidateResult.VALID
    else -> validateResult
}



