package com.goetu.go3timekeepingserver.timelog.presentation

sealed class PinCodeEvent{
    object Init: PinCodeEvent()
    object Refresh: PinCodeEvent()

}
