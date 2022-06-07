package com.gig.hue.com.gig.hue.data.repositories

import android.content.Context
import com.gig.hue.dao.UserDao
import com.gig.hue.dao.UserInformationDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val userDao: UserDao,
    private val userInformationDao: UserInformationDao) {




}