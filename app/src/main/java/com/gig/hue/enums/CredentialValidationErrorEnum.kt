package com.gig.hue.enums

import com.gig.hue.R

enum class CredentialValidationErrorEnum(val stringResource: Int? = null) {
    EMPTY_STRING(R.string.invalid_text), INVALID_USERNAME(R.string.invalid_email),
    INVALID_PASSWORD(R.string.invalid_password)
}