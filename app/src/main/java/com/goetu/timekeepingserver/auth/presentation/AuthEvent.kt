package com.goetu.go3timekeepingserver.timelog.presentation

sealed class AuthEvent {
    object Init: AuthEvent()
    object Login: AuthEvent()
    object Loading: AuthEvent()
    object Loaded: AuthEvent()
    object Error: AuthEvent()

}
