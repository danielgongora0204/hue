package com.gig.hue.com.gig.hue.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: String,
    val userDetail: String,
    val userInformation: String,
    val userRole: String,
    val client: String,
    val jwt: String,
    val refreshToken: String?,
    val userName: String,
    val password: String
)
