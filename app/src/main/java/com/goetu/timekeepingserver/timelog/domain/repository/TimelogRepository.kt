package com.goetu.go3timekeepingserver.timelog.domain.repository

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.coroutines.flow.Flow


interface TimelogRepository {

    suspend fun getTimelogs(
    ) : Flow<Resource<List<Timelog>>>
}