package com.goetu.go3timekeepingserver.timelog.domain.model

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val flag: String? = null,
    val userMessage: String? = null,
    val internalMessage: User? = null,
    val message: String? = null,
    val errors: Errors? = null
 )

@Serializable
data class Errors(
    val email: List<String>? = null
)
