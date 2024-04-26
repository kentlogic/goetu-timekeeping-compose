package com.goetu.go3timekeepingserver.di


import android.Manifest
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
 import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Singleton


private const val USER_PREFERENCES = "user_preferences"
// Preferences DataStore
private val Context.dataStore by preferencesDataStore(name = "user_preferences")
// Proto DataStore

object PreferencesKeys {
    val EMPLOYEE_RC = stringPreferencesKey("employee_rc")
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {

        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }


    @Singleton
    @Provides
     fun getIMEI(@ApplicationContext appContext: Context): String {
             var result: String? = null
            val hasReadPhoneStatePermission = appContext.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            val tm: TelephonyManager = appContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            try {
                if (hasReadPhoneStatePermission) result = tm.getDeviceId()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (result == null || result.length == 0) {
                result = null
            }
            return result.toString()
        }



        @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }


            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpRedirect) {
                checkHttpMethod = false
                allowHttpsDowngrade = true
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        // Load tokens from a local storage and return them as the 'BearerTokens' instance
                        BearerTokens(
                            "BNKL03L1IOH6N9OI4QO2I6XNKX2YGIS7",
                            "BNKL03L1IOH6N9OI4QO2I6XNKX2YGIS7"
                        )
                    }
                }
            }
        }
    }

}