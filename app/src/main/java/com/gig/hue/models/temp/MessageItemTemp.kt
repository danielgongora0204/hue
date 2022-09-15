package com.gig.hue.com.gig.hue.models.temp

import java.time.LocalDateTime

data class MessageItemTemp(
    val author: String,
    val message: String,
    val dateTime: LocalDateTime
    )
