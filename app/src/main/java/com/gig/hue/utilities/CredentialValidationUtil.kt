package com.gig.hue.com.gig.hue.utilities

import com.gig.hue.com.gig.hue.helper.constants.RegexConstants.EMAIL_ADDRESS_REGEX
import com.gig.hue.com.gig.hue.helper.constants.RegexConstants.PASSWORD_ADDRESS_REGEX
import com.gig.hue.enums.CredentialValidationErrorEnum
import com.gig.hue.enums.CredentialValidationErrorEnum.EMPTY_STRING
import com.gig.hue.enums.CredentialValidationErrorEnum.INVALID_USERNAME
import com.gig.hue.enums.CredentialValidationErrorEnum.INVALID_PASSWORD
import com.gig.hue.utilities.extensions.default
import java.util.regex.Pattern

object CredentialValidationUtil {
    private val emailPattern: Pattern = Pattern.compile(EMAIL_ADDRESS_REGEX)

    private val passwordPattern: Pattern = Pattern.compile(PASSWORD_ADDRESS_REGEX)

    fun checkUsername(username: String?): CredentialValidationErrorEnum? = when {
        username.isNullOrEmpty() -> EMPTY_STRING
        !emailPattern.matcher(username.default("")).matches() -> INVALID_USERNAME
        else -> null
    }

    fun checkPassword(password: String?): CredentialValidationErrorEnum? = when {
        password.isNullOrEmpty() -> EMPTY_STRING
        password.default("").length <= 6 ||
                !passwordPattern.matcher(password.default("")).matches() -> INVALID_PASSWORD
        else -> null
    }
}