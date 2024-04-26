package com.goetu.go3timekeepingserver.timelog.remote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(

    val employee_rc: String? = null,
    val employee_id: Int? = null,
    val role: Int? = null
)
