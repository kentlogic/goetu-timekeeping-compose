package com.goetu.go3timekeepingserver.timelog.domain.model

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog
import kotlinx.serialization.Serializable

@Serializable
data class TimelogResponse(
    val flag: String,
    val userMessage: String,
    val internalMessage: List<Timelog>,
)
