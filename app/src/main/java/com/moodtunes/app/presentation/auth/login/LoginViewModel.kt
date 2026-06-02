package com.moodtunes.app.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.moodtunes.app.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(signInUseCase: SignInUseCase) : ViewModel() {
}