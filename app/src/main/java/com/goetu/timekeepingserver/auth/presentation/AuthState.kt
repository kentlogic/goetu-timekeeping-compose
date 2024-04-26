package com.goetu.go3timekeepingserver.timelog.presentation

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.User

data class AuthState(
    val authResponse: User? = null,
    val isLoading: Boolean = false,
    val authError: Boolean = false,
    val authErrorMessage: String? ="",
    val isLoaded: Boolean = false
)
