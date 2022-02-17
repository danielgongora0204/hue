package com.gig.cryptolayer

import java.lang.RuntimeException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptoClient {

    @JvmStatic
    fun encrypt(valueToEncrypt: String, secretKey: String) =
        AES256.encrypt(valueToEncrypt, secretKey)

    @JvmStatic
    fun decrypt(
        valueToDecrypt: String,
        secretKey: String,
        returnSameValueIfError: Boolean = false
    ) =
        AES256.decrypt(valueToDecrypt, secretKey, returnSameValueIfError)
}

private object AES256 {

    private fun cipher(mode: Int, secretKey: String, ivArray: ByteArray): Cipher {
        if (secretKey.length != 32) throw RuntimeException("Secret key should be 32 characters")

        val c = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        val sk = SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(ivArray)
        c.init(mode, sk, iv)
        return c
    }

    private fun generateIvArray(secretKey: String): ByteArray {
        return secretKey.substring(0, 16).toByteArray()
    }

    fun encrypt(str: String, secretKey: String): String {
        if (str.isBlank()) return str

        return try {
            val ivArray = generateIvArray(secretKey)
            val encrypted =
                cipher(Cipher.ENCRYPT_MODE, secretKey, ivArray).doFinal(str.toByteArray())
            val result = ByteArray(ivArray.size + encrypted.size)
            System.arraycopy(ivArray, 0, result, 0, ivArray.size)
            System.arraycopy(encrypted, 0, result, ivArray.size, encrypted.size)
            Base64.getEncoder().encodeToString(result).trim()
           // Base64.encodeToString(result, Base64.DEFAULT).trim()
        } catch (e: Exception) {
            ""
        }
    }

    fun decrypt(str: String, secretKey: String, returnSameValueIfError: Boolean = false): String {
        if (str.isBlank()) return str

        return try {
            val newStr = str.replace(' ', '+')
            val fullCipher = Base64.getDecoder().decode(newStr.toByteArray())

            val ivArray = ByteArray(16)
            val cipher = ByteArray(fullCipher.size - ivArray.size)

            System.arraycopy(fullCipher, 0, ivArray, 0, ivArray.size)
            System.arraycopy(fullCipher, ivArray.size, cipher, 0, fullCipher.size - ivArray.size)




            String(
                cipher(Cipher.DECRYPT_MODE, secretKey, ivArray).doFinal(cipher),
                Charsets.UTF_8
            ).trim()
        } catch (e: Exception) {
            if (returnSameValueIfError) {
                str
            } else {
                ""
            }
        }
    }
}


