package com.gig.hue.com.gig.hue.utilities

import android.content.Context

class ResourceComparer {
    fun isEqual(context: Context, resId: Int, string: String) : Boolean {
        return context.getString(resId) == string
    }
}