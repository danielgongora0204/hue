package com.gig.hue.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gig.hue.data.Converters
import com.gig.hue.models.database.User
import com.gig.hue.models.database.UserInformation
import com.gig.hue.dao.UserDao
import com.gig.hue.dao.UserInformationDao
import com.gig.hue.helper.constants.DatabaseConstants


@Database(
    entities = [User::class, UserInformation::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HueDatabase : RoomDatabase()  {

    abstract fun userDao(): UserDao
    abstract fun userInformationDao() : UserInformationDao

    companion object {
        @Volatile
        private var instance: HueDatabase? = null

        fun getDatabase(context: Context): HueDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, HueDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                    .build().also { instance = it }
            }
        }

        //private fun buildDatabase(context: Context): HueDatabase {
        //    return Room.databaseBuilder(context, HueDatabase::class.java, DatabaseConstants.DATABASE_NAME)
        //       .build()
        //}
    }


}