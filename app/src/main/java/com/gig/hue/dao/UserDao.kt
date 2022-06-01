package com.gig.hue.dao

import androidx.room.Dao
import androidx.room.Query
import com.gig.hue.models.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM User LIMIT 1")
    suspend fun getLoggedInUser(): Flow<User?>

    @Query("DELETE FROM User")
    suspend fun deleteAll()

}