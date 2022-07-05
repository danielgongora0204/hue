package com.gig.hue.data.repositories

import com.gig.hue.dao.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashRepository @Inject constructor(private val userDao: UserDao) {

    fun isUserLoggedIn(): Flow<Boolean> = userDao.isUserLoggedIn();

}