package com.gig.hue.models.temp

import java.time.LocalDateTime

data class ConversationItemTemp(
    val conversation: String,
    val lastMessage: String,
    val relatedLocation: String,
    val dateTime: LocalDateTime,
    val pendingMessage: Int? = null
)