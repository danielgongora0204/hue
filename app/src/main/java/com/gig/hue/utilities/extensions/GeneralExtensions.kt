package com.gig.hue.utilities.extensions

fun <T> T?.default(default: T): T {
    return this ?: default
}