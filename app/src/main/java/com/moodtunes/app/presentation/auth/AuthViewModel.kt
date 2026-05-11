package com.moodtunes.app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            // TODO: integrate with auth provider
            preferencesManager.isLoggedIn = true
            _state.value = _state.value.copy(isLoading = false, isAuthenticated = true)
        }
    }

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            // TODO: integrate with auth provider
            preferencesManager.isLoggedIn = true
            _state.value = _state.value.copy(isLoading = false, isAuthenticated = true)
        }
    }

    fun signOut() {
        preferencesManager.isLoggedIn = false
        _state.value = AuthState()
    }

    fun markOnboardingComplete() {
        preferencesManager.hasSeenOnboarding = true
    }
}
