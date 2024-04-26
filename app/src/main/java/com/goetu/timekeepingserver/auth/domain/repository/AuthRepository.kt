package com.goetu.go3timekeepingserver.timelog.domain.repository

import com.goetu.go3timekeepingserver.timelog.domain.model.LoginResponse
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun login(email: String, password: String
    ) : Flow<Resource<User>>
}