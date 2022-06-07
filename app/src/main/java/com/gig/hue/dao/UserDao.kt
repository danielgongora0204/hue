package com.gig.hue.dao

import androidx.room.Dao
import androidx.room.Query
import com.gig.hue.models.database.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao: BaseDao<User> {

    @Query("SELECT * FROM user LIMIT 1")
    fun getLoggedInUser(): Flow<User?>

    @Query("SELECT EXISTS(SELECT 1 FROM user LIMIT 1)")
    fun isUserLoggedIn(): Flow<Boolean>

    @Query("DELETE FROM user")
    fun deleteAll()
}