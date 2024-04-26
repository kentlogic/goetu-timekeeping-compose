package com.goetu.go3timekeepingserver.timelog.remote.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PinCode(
    val pincode: Int? = 1234,

)
