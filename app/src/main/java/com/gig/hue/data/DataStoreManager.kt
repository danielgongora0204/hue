package com.gig.hue.com.gig.hue.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.gig.hue.com.gig.hue.helper.constants.DataStoreConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(DataStoreConstants.DATA_STORE_NAME)

    fun readBoolean(key: Preferences.Key<Boolean>): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    fun readString(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    fun readInt(key: Preferences.Key<Int>): Flow<Int?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun writeString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    suspend fun writeBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    suspend fun writeInt(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    companion object PreferencesKeys {
        val REMEMBER_ME = preferencesKey<Boolean>(DataStoreConstants.DATA_STORE_LOGIN_REMEMBER_ME)
        val LOGIN_USERNAME = preferencesKey<String>(DataStoreConstants.DATA_STORE_LOGIN_USERNAME)
        val LOGIN_PASSWORD = preferencesKey<String>(DataStoreConstants.DATA_STORE_LOGIN_PASSWORD)
    }
}