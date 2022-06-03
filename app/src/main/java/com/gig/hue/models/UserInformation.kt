package com.gig.hue.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_information")
data class UserInformation(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "maternalLastName") val maternalLastName: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "contactEmail") val contactEmail: String,
    @ColumnInfo(name = "webPage") val webPage: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "profilePictureUrl") val profilePictureUrl: String?
)
