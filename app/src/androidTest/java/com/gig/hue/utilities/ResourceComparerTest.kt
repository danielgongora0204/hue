package com.gig.hue.utilities

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.gig.hue.com.gig.hue.utilities.ResourceComparer
import com.gig.hue.R
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResourceComparerTest {

    private lateinit var resourceComparer: ResourceComparer

    @BeforeEach
    fun setUp() {
        resourceComparer = ResourceComparer()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `checks if wrong equal returns false`() {
        //Given
        val textToCheck = "Hello"
        val resourceToCheck = R.string.login_forget_password
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //When
        val result = resourceComparer.isEqual(context, resourceToCheck, textToCheck)
        //Then
        assertThat(result).isFalse()
    }

    @Test
    fun `checks if right equal returns true`() {
        //Given
        val textToCheck = "Forget password?"
        val resourceToCheck = R.string.login_forget_password
        val context = ApplicationProvider.getApplicationContext<Context>()
        //When
        val result = resourceComparer.isEqual(context, resourceToCheck, textToCheck)
        //Then
        assertThat(result).isTrue()
    }
}