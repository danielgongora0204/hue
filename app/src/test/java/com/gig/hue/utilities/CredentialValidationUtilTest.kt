package com.gig.hue.utilities

import com.gig.hue.com.gig.hue.utilities.CredentialValidationUtil
import com.gig.hue.enums.CredentialValidationErrorEnum
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CredentialValidationUtilTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `checks result if username is empty`() {
        //Given
        val username = ""
        //When
        val result = CredentialValidationUtil.checkUsername(username)
        //Then
        assertThat(result).isEqualTo(CredentialValidationErrorEnum.EMPTY_STRING)
    }

    @Test
    fun `checks result if username has wrong format`() {
        //Given
        val username = "daniel"
        //When
        val result = CredentialValidationUtil.checkUsername(username)
        //Then
        assertThat(result).isEqualTo(CredentialValidationErrorEnum.INVALID_USERNAME)
    }

    @Test
    fun `checks result if username is valid`() {
        //Given
        val username = "daniel@gmail.com"
        //When
        val result = CredentialValidationUtil.checkUsername(username)
        //Then
        assertThat(result).isNull()
    }

    @Test
    fun `checks result if password is empty`() {
        //Given
        val password = ""
        //When
        val result = CredentialValidationUtil.checkPassword(password)
        //Then
        assertThat(result).isEqualTo(CredentialValidationErrorEnum.EMPTY_STRING)
    }

    @Test
    fun `checks result if password has wrong format`() {
        //Given
        val lowercasePassword = "abcefg123"
        val shortPassword = "qwe"
        val noNumbersPassword = "abcEFG"
        //When
        val result = CredentialValidationUtil.checkPassword(lowercasePassword)
        //Then
        assertThat(result).isEqualTo(CredentialValidationErrorEnum.INVALID_PASSWORD)
    }

    @Test
    fun `checks result if password is valid`() {
        //Given
        val lowercasePassword = "abcEFG123!"
        //When
        val result = CredentialValidationUtil.checkPassword(lowercasePassword)
        //Then
        assertThat(result).isNull()
    }
}