package com.goetu.go3timekeepingserver.timelog.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goetu.go3timekeepingserver.timelog.domain.repository.PinCodeRepository
import com.goetu.go3timekeepingserver.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor (
    private val repository: PinCodeRepository
        ): ViewModel(){

            var state by mutableStateOf(PinCodeState())

    fun onEvent(event: PinCodeEvent) {
        when (event) {
            is PinCodeEvent.Refresh -> {

                Log.i("sds", "PinCodeEvent.Refresh ${state.isLoading}\n\n\n")

                getPinCode()
            }
            is PinCodeEvent.Init -> {
                Log.i("sds", "PinCodeEvent.Init ${state.isLoading}\n\n\n")
                getPinCode()
            }
            else -> {}
        }
    }

    fun getPinCode() {
        viewModelScope.launch {
            repository
                .getPinCode()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                        result.data?.let {pin ->
                            state = state.copy(
                                pinCode = pin,
                                isLoading = false
                            )
                        }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
             


                    }
                }
        }
    }
}