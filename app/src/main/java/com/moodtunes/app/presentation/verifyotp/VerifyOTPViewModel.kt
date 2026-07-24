package com.moodtunes.app.presentation.verifyotp

import com.moodtunes.app.MoodTunesApp
import com.moodtunes.app.R
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.usecase.auth.VerifyOTPUseCase
import com.moodtunes.app.presentation.state.BaseViewModel
import com.moodtunes.app.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import utils.NetworkUtils
import javax.inject.Inject

@HiltViewModel
class VerifyOTPViewModel @Inject constructor(
    private val verifyOTPUseCase: VerifyOTPUseCase,
    private val networkUtils: NetworkUtils,
) : BaseViewModel() {

    private val _uiStateVerifyOTP = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiStateVerifyOTP: StateFlow<UiState<String>> = _uiStateVerifyOTP

    fun verifyOTP(otpCode: String, email: String) = launch {
        if (!networkUtils.isInternetAvailable()) {
            _uiStateVerifyOTP.value =
                UiState.Error(MoodTunesApp.context.getString(R.string.error_no_internet))
            return@launch
        }
        if (otpCode.isEmpty()) {
            _uiStateVerifyOTP.value =
                UiState.Error(MoodTunesApp.context.getString(R.string.error_empty_otp))
            return@launch
        }
        _uiStateVerifyOTP.value = UiState.Loading
        val result = verifyOTPUseCase.invoke(email, otpCode)

        _uiStateVerifyOTP.value = when (result) {
            is DataResult.Success -> {
                UiState.Success(result.data)
            }

            is DataResult.Error -> {
                UiState.Error(result.message)
            }
        }

    }

    fun resetState() {
        _uiStateVerifyOTP.value = UiState.Idle
    }
}