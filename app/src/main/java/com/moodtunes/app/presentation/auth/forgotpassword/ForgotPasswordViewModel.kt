package com.moodtunes.app.presentation.auth.forgotpassword

import com.moodtunes.app.MoodTunesApp
import com.moodtunes.app.R
import com.moodtunes.app.data.local.UserPreferences
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.usecase.auth.ForgotPasswordUseCase
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
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val networkUtils: NetworkUtils,
    private val userPreferences: UserPreferences
) : BaseViewModel() {

    private val _uiStateForgotPwd = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiStateForgotPwd: StateFlow<UiState<String>> = _uiStateForgotPwd.asStateFlow()

    fun forgotPassword(email: String) = launch {
        if (!networkUtils.isInternetAvailable()) {
            _uiStateForgotPwd.value =
                UiState.Error(MoodTunesApp.context.getString(R.string.error_no_internet))
            return@launch
        }
        //Validate data
        if (validateForgotPwdData(email)) return@launch

        _uiStateForgotPwd.value = UiState.Loading
        val result = forgotPasswordUseCase.invoke(email)
        _uiStateForgotPwd.value = when (result) {
            is DataResult.Success -> UiState.Success(result.data)
            is DataResult.Error -> {
                UiState.Error(result.message)
            }
        }

    }

    private fun validateForgotPwdData(email: String): Boolean {
        val errorMsg = when {
            email.isEmpty() -> R.string.error_please_enter_your_email
            !email.isValidEmail() -> R.string.error_please_enter_a_valid_email
            else -> null
        }
        return if (errorMsg != null) {
            _uiStateForgotPwd.value = UiState.Error(MoodTunesApp.context.getString(errorMsg))
            true
        } else {
            false
        }
    }
    fun resetState() {
        _uiStateForgotPwd.value = UiState.Idle
    }
}
