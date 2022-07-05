package com.gig.hue.data.repositories

import com.gig.hue.dao.UserDao
import com.gig.hue.dao.UserInformationDao
import com.gig.hue.data.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val userDao: UserDao,
    private val userInformationDao: UserInformationDao,
    private val dataStoreManager: DataStoreManager) {

    fun getDataStoreUsername(): Flow<String> = dataStoreManager.readSecuredValue(DataStoreManager.LOGIN_USERNAME)

    fun getDataStorePassword(): Flow<String> = dataStoreManager.readSecuredValue(DataStoreManager.LOGIN_PASSWORD)



}