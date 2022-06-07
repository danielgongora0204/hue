package com.gig.hue.dao

import androidx.room.Dao
import androidx.room.Query
import com.gig.hue.models.database.UserInformation

import kotlinx.coroutines.flow.Flow

@Dao
interface UserInformationDao: BaseDao<UserInformation> {
    @Query("SELECT * FROM user_information WHERE id = :id")
    fun getById(id: Int): Flow<UserInformation?>

    @Query("SELECT * FROM user_information")
    fun getAll(): Flow<List<UserInformation>>

    @Query("DELETE FROM user_information")
    suspend fun deleteAll()
}