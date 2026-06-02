package com.moodtunes.app.presentation.splash

sealed class SplashState {
    object Loading : SplashState()
    object GoSignIn : SplashState()
    object GoHome : SplashState()
}