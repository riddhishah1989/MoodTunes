package com.moodtunes.app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.data.local.PreferencesManager
import com.moodtunes.app.domain.repository.MoodTunesRepository
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
    private val repository: MoodTunesRepository,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.signIn(email, password)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, isAuthenticated = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repository.signUp(name, email, password)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, isAuthenticated = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
            _state.value = AuthState()
        }
    }

    fun markOnboardingComplete() {
        preferencesManager.hasSeenOnboarding = true
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}
