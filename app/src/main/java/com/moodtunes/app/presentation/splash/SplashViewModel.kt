package com.moodtunes.app.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SplashState {
    object GoSignIn : SplashState()
    object GoHome : SplashState()
}

@HiltViewModel
class SplashViewModel @Inject constructor(private val userPreferences: UserPreferences) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state

    init {
        checkUserState()
    }

    private fun checkUserState() {
        viewModelScope.launch {
            // Minimum 2 seconds to show your custom splash UI
            delay(2000)
            val isLoggedIn = userPreferences.isLoggedIn()
            _state.value = if (isLoggedIn) SplashState.GoHome else SplashState.GoSignIn
        }
    }
}