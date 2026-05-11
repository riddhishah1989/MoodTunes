package com.moodtunes.app.presentation.splash

import androidx.lifecycle.ViewModel
import com.moodtunes.app.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val isLoggedIn: Boolean get() = preferencesManager.isLoggedIn
    val hasSeenOnboarding: Boolean get() = preferencesManager.hasSeenOnboarding
}
