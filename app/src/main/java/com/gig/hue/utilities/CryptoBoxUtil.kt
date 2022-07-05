package com.gig.hue.utilities

import com.gig.cryptobox.CryptoClient
import com.gig.hue.helper.constants.SecurityConstants

object CryptoBoxUtil {
    fun encrypt(value: String): String = CryptoClient.encrypt(value, SecurityConstants.CRYPTO_KEY)

    fun decrypt(value: String, returnSameValueIfError: Boolean = false): String = CryptoClient.decrypt(value, SecurityConstants.CRYPTO_KEY, returnSameValueIfError)

    fun decryptNullable(value: String?): String? {
        if (value.isNullOrBlank()) {
            return null
        }
        return CryptoClient.decrypt(value, SecurityConstants.CRYPTO_KEY)
    }
}