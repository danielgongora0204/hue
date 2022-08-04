package com.gig.hue.data.repositories

import com.gig.hue.dao.UserDao
import com.gig.hue.dao.UserInformationDao
import com.gig.hue.data.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val userDao: UserDao,
    private val userInformationDao: UserInformationDao,
    private val dataStoreManager: DataStoreManager) {

    fun getDataStoreUsername(): Flow<String> = dataStoreManager.readSecuredValue(DataStoreManager.LOGIN_USERNAME)

    fun getDataStorePassword(): Flow<String> = dataStoreManager.readSecuredValue(DataStoreManager.LOGIN_PASSWORD)

    suspend fun postLogin(): Boolean {
        delay(5000L)
        return loginDemoResult()
    }

    //TODO: This will be replaced by the backend login call
    private fun loginDemoResult(): Boolean = (0..100).random().let{ it % 2 != 1 }

}