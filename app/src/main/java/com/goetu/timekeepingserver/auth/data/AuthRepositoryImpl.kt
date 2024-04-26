package com.goetu.go3timekeepingserver.timelog.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import coil.network.HttpException
import com.goetu.go3timekeepingserver.di.PreferencesKeys
import com.goetu.go3timekeepingserver.timelog.domain.model.LoginResponse
import com.goetu.go3timekeepingserver.timelog.domain.repository.AuthRepository
import com.goetu.go3timekeepingserver.timelog.remote.AuthServiceImpl
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.LoginRequest
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User
import com.goetu.go3timekeepingserver.util.Resource
 import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthServiceImpl,
    private val dataStore:  DataStore<Preferences>
) : AuthRepository {


    override suspend fun login(email: String, password: String): Flow<Resource<User>> {
        return flow {
            try {
                emit(Resource.Loading(true))

                val authResponse = try {
                    api.login(
                        LoginRequest(
                            email = email,
                            password = password,
                            login_type = "TIMEKEEPING"
                        )
                    )

                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error("Unable to Login [$e]"))
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error("Unable to Login [$e]"))
                } catch (e: Exception) {
                    emit(Resource.Error("Unable to Login [$e]"))
                }


                authResponse.let { auth ->
                    val response = auth as LoginResponse
                    emit(Resource.Loading(false))


                    if (response.flag == "success") {
                        dataStore.edit { preferences ->
                            preferences[PreferencesKeys.EMPLOYEE_RC] = (response.internalMessage as User).employee_rc.toString()
                        }
                        emit(Resource.Success(response.internalMessage as User))
                    } else {
                        emit(Resource.Error(auth.userMessage.toString()))

                    }

                }
            } catch (e: Exception) {

                emit(Resource.Loading(false))

                emit(Resource.Error("Unable to Login [$e]"))

            }
        }
    }


}