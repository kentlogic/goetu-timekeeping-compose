package com.goetu.go3timekeepingserver.timelog.remote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
    val login_type: String
)
