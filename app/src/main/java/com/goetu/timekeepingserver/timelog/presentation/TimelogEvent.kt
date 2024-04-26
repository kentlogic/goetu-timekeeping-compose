package com.goetu.go3timekeepingserver.timelog.presentation

sealed class TimelogEvent{
    object Init: TimelogEvent()
    object Refresh: TimelogEvent()

}
