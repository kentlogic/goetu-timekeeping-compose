package com.goetu.go3timekeepingserver.timelog.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ImeiRequest(
    val imei: String,
)
