package com.goetu.go3timekeepingserver.timelog.domain.model

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode
import kotlinx.serialization.Serializable

@Serializable
data class PinCodeResponse(
    val flag: String,
    val userMessage: String,
    val internalMessage: PinCode,
)
