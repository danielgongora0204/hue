package com.gig.hue.data

import androidx.room.Database
import com.gig.hue.models.*



@Database(
    entities = [User::class, UserInformation::class],
    version = 1,
    exportSchema = false
)
class AppDatabase {

}