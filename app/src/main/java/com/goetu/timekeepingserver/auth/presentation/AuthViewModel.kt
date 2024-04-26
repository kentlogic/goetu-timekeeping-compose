package com.goetu.go3timekeepingserver.timelog.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goetu.go3timekeepingserver.timelog.domain.repository.AuthRepository
import com.goetu.go3timekeepingserver.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val repository: AuthRepository,
    private val providePreferencesDataStore: DataStore<Preferences>
         ): ViewModel(){

            var state by mutableStateOf(AuthState())
    
    fun onEvent(event: AuthEvent, email:String, password: String) {
        when (event) {
            is AuthEvent.Login -> {
              login(email, password)
            }

            else -> {}
        }
    }

    class UserPreferencesRepository @Inject constructor(
        private val dataStore: DataStore<Preferences>
    )

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository
                .login(email, password)
                .collect { result ->
                    Log.i("Sdsd"," ${result} asdsadad")
                    when (result) {
                        is Resource.Success -> {
                        result.data?.let {
                            //providePreferencesDataStore.
                            state = state.copy(
                                authResponse = it,
                                isLoaded = true
                             )
                        }
                        }
                        is Resource.Error -> {
                            Log.i("Sdsd"," ErrorError asdsada ${result.message }")

                                state = state.copy(
                                    authErrorMessage =result.message,
                                    authError = true
                                )

                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }


                        else -> {}
                    }
                }
        }
    }
}