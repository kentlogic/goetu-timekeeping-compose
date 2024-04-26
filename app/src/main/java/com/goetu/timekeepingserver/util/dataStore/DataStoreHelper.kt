package com.goetu.go3timekeepingserver.util.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class DataStoreHelper(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_EMAIL_KEY1 = intPreferencesKey(1.toString())

    }

    //get the saved email
    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: "FirstLast@gmail.com"
        }

    //get string value
    fun getString(key:Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: ""
            }
    }

    //get int value
    fun getInt(key:Preferences.Key<Int>): Flow<Int?> {
        return context.dataStore.data
            .map { preferences ->
                preferences[key] ?: 1
            }
    }


    //save string into datastore
    suspend fun saveString(key:  Preferences.Key<String>, value : String) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    //save int into datastore
    suspend fun saveInt(key:  Preferences.Key<Int>, value : Int) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }


}