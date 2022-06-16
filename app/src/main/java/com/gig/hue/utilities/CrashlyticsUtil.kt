package com.gig.hue.com.gig.hue.utilities

import android.content.Context
import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsUtil {

    fun recordError(t: Throwable, userId: String? = null) {
        val crashlyticsInstance = FirebaseCrashlytics.getInstance()
        userId?.let {
            crashlyticsInstance.setUserId(userId)
        }
        crashlyticsInstance.recordException(t)
    }

}