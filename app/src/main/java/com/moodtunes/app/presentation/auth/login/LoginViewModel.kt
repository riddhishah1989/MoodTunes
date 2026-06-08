package com.moodtunes.app.presentation.auth.login

import com.moodtunes.app.MoodTunesApp
import com.moodtunes.app.R
import com.moodtunes.app.data.local.UserPreferences
import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.usecase.auth.SignInUseCase
import com.moodtunes.app.presentation.state.BaseViewModel
import com.moodtunes.app.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import utils.CommonUtilities.isValidEmail
import utils.NetworkUtils
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: SignInUseCase,
    private val networkUtils: NetworkUtils,
    private val userPreferences: UserPreferences
) : BaseViewModel() {
    private val _uiStateLogin = MutableStateFlow<UiState<Auth>>(UiState.Idle)
    val uiStateLogin: StateFlow<UiState<Auth>> = _uiStateLogin.asStateFlow()

    fun loginUser(email: String, password: String) = launch {
        if (!networkUtils.isInternetAvailable()) {
            _uiStateLogin.value =
                UiState.Error(MoodTunesApp.context.getString(R.string.error_no_internet))
            return@launch
        }
        //Validate login data
        if (validateLoginData(email,password)) return@launch

        _uiStateLogin.value = UiState.Loading
        val result = loginUseCase.invoke(email,password)

        _uiStateLogin.value = when (result) {
            is DataResult.Success -> {
                userPreferences.saveToken(result.data.token)
                userPreferences.saveUser(result.data.user)
                UiState.Success(result.data)
            }

            is DataResult.Error -> {
                UiState.Error(result.message)
            }
        }
    }

    private fun validateLoginData(email: String, password: String): Boolean {
        val errorMsg = when {
            email.isEmpty() -> R.string.error_please_enter_your_email
            password.isEmpty() -> R.string.error_please_enter_your_password
            !email.isValidEmail() -> R.string.error_please_enter_a_valid_email
            else -> null
        }
        return if (errorMsg != null) {
            _uiStateLogin.value = UiState.Error(MoodTunesApp.context.getString(errorMsg))
            true
        } else {
            false
        }
    }

    fun resetState() {
        _uiStateLogin.value = UiState.Idle
    }
}