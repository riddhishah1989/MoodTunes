package com.moodtunes.app.presentation.resetpassword

import com.moodtunes.app.MoodTunesApp
import com.moodtunes.app.R
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.usecase.auth.ResetPasswordUseCase
import com.moodtunes.app.presentation.state.BaseViewModel
import com.moodtunes.app.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import utils.CommonUtilities.isValidEmail
import utils.CommonUtilities.isValidPassword
import utils.NetworkUtils
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val networkUtils: NetworkUtils
) :
    BaseViewModel() {
    private val _uiStateResetPassword = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiStateResetPassword: StateFlow<UiState<String>> = _uiStateResetPassword

    fun resetPassword(email: String, newPassword: String, confirmPassword: String) = launch {
        if (!networkUtils.isInternetAvailable()) {
            _uiStateResetPassword.value =
                UiState.Error(MoodTunesApp.context.getString(R.string.error_no_internet))
            return@launch
        }

        if (validateResetPasswordData(email, newPassword, confirmPassword)) return@launch
        _uiStateResetPassword.value = UiState.Loading
        val result = resetPasswordUseCase.invoke(email, newPassword, confirmPassword)
        _uiStateResetPassword.value = when (result) {
            is DataResult.Success -> {
                UiState.Success(result.data)
            }

            is DataResult.Error -> {
                UiState.Error(result.message)
            }
        }
    }

    private fun validateResetPasswordData(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val errorMsg = when {
            email.isEmpty() -> R.string.error_please_enter_your_email
            password.isEmpty() -> R.string.error_please_enter_your_password
            confirmPassword.isEmpty() -> R.string.error_please_enter_your_password
            !email.isValidEmail() -> R.string.error_please_enter_a_valid_email
            !password.isValidPassword() -> R.string.error_please_enter_valid_password
            password != confirmPassword -> R.string.error_password_not_match
            else -> null
        }
        return if (errorMsg != null) {
            _uiStateResetPassword.value = UiState.Error(MoodTunesApp.context.getString(errorMsg))
            true
        } else {
            false
        }

    }
    fun resetState() {
        _uiStateResetPassword.value = UiState.Idle
    }
}