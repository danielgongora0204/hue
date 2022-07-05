package com.gig.hue.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.gig.hue.utilities.CryptoBoxUtil
import com.gig.hue.helper.constants.DataStoreConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DataStoreConstants.DATA_STORE_NAME)

    private inline fun <T> Flow<Preferences>.localMap(crossinline fetchValue: (value: Preferences) -> T): Flow<T> =
        catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            fetchValue(it)
        }


    private inline fun <reified T> Flow<Preferences>.secureMap(crossinline fetchValue: (value: Preferences) -> String): Flow<T> =
        catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            val decryptedValue = CryptoBoxUtil.decrypt(fetchValue(it))
            Json.decodeFromString(decryptedValue)
        }


    private suspend inline fun <reified T> DataStore<Preferences>.secureEdit(
        value: T,
        crossinline editStore: (MutablePreferences, String) -> Unit
    ) {
        edit {
            val encryptedValue = CryptoBoxUtil.encrypt(Json.encodeToString(value))
            editStore.invoke(it, encryptedValue)
        }
    }

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) =
        context.dataStore.edit {
            it[key] = value
        }


    fun <T> readValue(key: Preferences.Key<T>): Flow<T> =
        context.dataStore.data.localMap {
            it[key]!!
        }


    //Only Meant for String Values
    suspend fun storeSecuredValue(key: Preferences.Key<String>, value: String) =
        context.dataStore.secureEdit(value) { prefs, encryptedValue ->
            prefs[key] = encryptedValue
        }


    fun readSecuredValue(key: Preferences.Key<String>): Flow<String> =
        context.dataStore.data.secureMap { preferences ->
            preferences[key].orEmpty()
        }


    companion object PreferencesKeys {
        val REMEMBER_ME = booleanPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_REMEMBER_ME)
        val LOGIN_USERNAME = stringPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_USERNAME)
        val LOGIN_PASSWORD = stringPreferencesKey(DataStoreConstants.DATA_STORE_LOGIN_PASSWORD)
    }
}


