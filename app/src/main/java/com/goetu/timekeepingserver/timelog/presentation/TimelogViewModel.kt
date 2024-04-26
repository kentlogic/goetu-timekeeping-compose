package com.goetu.go3timekeepingserver.timelog.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goetu.go3timekeepingserver.timelog.domain.repository.TimelogRepository
import com.goetu.go3timekeepingserver.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelogViewModel @Inject constructor (
    private val repository: TimelogRepository
        ): ViewModel(){

            var state by mutableStateOf(TimelogState())



    fun onEvent(event: TimelogEvent) {
        when (event) {
            is TimelogEvent.Refresh -> {

                Log.i("sds", "TimelogEvent.Refresh ${state.isLoading}\n\n\n")

                getTimelogs()
            }
            is TimelogEvent.Init -> {
                Log.i("sds", "TimelogEvent.Init ${state.isLoading}\n\n\n")
                getTimelogs()
            }
        }
    }

    fun getTimelogs() {
        viewModelScope.launch {
            repository
                .getTimelogs()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                        result.data?.let {timelogList ->
                            state = state.copy(
                                timelogs = timelogList,
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