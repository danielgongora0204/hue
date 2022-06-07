package com.gig.hue.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow


interface BaseDao<T> {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg values: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg values: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)

    @Delete
    suspend fun delete(vararg values: T)

}