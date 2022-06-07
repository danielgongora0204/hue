package com.gig.hue.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.gig.hue.helper.constants.DataStoreConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreConstants.DATA_STORE_NAME)

    private suspend fun <T> DataStore<Preferences>.getFromLocalStorage(
        PreferencesKey: Preferences.Key<T>, func: T.() -> Unit) {
        data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            it[PreferencesKey]
        }.collect {
            it?.let { func.invoke(it as T) }
        }
    }

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun <T> readValue(key: Preferences.Key<T>, responseFunc: T.() -> Unit) {
        context.dataStore.getFromLocalStorage(key) {
            responseFunc.invoke(this)
        }
    }

    companion object PreferencesKeys {
        val REMEMBER_ME = booleanPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_REMEMBER_ME)
        val LOGIN_USERNAME = stringPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_USERNAME)
        val LOGIN_PASSWORD = stringPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_PASSWORD)
    }
}