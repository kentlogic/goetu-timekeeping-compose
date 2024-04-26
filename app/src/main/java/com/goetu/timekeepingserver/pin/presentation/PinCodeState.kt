package com.goetu.go3timekeepingserver.timelog.presentation

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.PinCode

data class PinCodeState(
    val pinCode: PinCode? = null,
    val isLoading: Boolean = false,
)
