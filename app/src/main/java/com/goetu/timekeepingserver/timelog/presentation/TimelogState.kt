package com.goetu.go3timekeepingserver.timelog.presentation

import com.goetu.go3timekeepingserver.timelog.remote.domain.model.Timelog

data class TimelogState(
    val timelogs: List<Timelog> = emptyList(),
    val isLoading: Boolean = false,
)
