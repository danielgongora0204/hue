package com.gig.hue.dao

import androidx.room.Query
import com.gig.hue.models.UserInformation
import kotlinx.coroutines.flow.Flow

interface UserInformationDao: BaseDao<UserInformation> {
    @Query("SELECT * FROM UserInformation WHERE id = :id")
    suspend fun getById(id: Int): Flow<UserInformation?>

    @Query("SELECT * FROM UserInformation")
    suspend fun getAll(): Flow<List<UserInformation>>

    @Query("DELETE FROM UserInformation")
    suspend fun deleteAll()
}