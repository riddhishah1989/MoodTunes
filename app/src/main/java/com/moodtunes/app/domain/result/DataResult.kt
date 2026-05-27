package com.moodtunes.app.domain.result

import com.moodtunes.app.presentation.state.UiState

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
}

/** Converts DataResult → UiState — used in every ViewModel */
fun <T> DataResult<T>.toUiState(): UiState<T> =
    when (this) {
        is DataResult.Success -> UiState.Success(data)
        is DataResult.Error   -> UiState.Error(message)
    }
