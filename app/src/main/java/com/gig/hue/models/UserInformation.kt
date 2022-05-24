package com.gig.hue.com.gig.hue.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformation(
    @PrimaryKey val id: String,
    val name: String,
    val lastName: String,
    val maternalLastName: String,
    val phoneNumber: String,
    val contactEmail: String,
    val webPage: String?,
    val address: String?,
    val profilePictureUrl: String?
)
