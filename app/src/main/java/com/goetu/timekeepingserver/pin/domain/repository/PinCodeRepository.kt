package com.goetu.go3timekeepingserver.timelog.domain.repository

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.coroutines.flow.Flow


interface PinCodeRepository {

    suspend fun getPinCode(
    ) : Flow<Resource<PinCode>>
}