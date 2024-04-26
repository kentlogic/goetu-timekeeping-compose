package com.goetu.go3timekeepingserver.timelog.domain.model

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User
import com.goetu.go3timekeepingserver.util.Resource
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class LoginBaseResponse(
    val flag: String? = null,
    val userMessage: String? = null,
     val message: String? = null,
    val errors: Errors? = null
 )
