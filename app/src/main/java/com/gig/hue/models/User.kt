package com.gig.hue.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "userDetail") val userDetail: String,
    @ColumnInfo(name = "userInformation") val userInformation: String,
    @ColumnInfo(name = "userRole") val userRole: String,
    @ColumnInfo(name = "client") val client: String,
    @ColumnInfo(name = "jwt") val jwt: String,
    @ColumnInfo(name = "refreshToken") val refreshToken: String?,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "password") val password: String
)
