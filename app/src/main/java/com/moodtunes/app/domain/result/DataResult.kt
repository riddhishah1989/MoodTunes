package com.moodtunes.app.domain.result

import com.moodtunes.app.presentation.state.UiState

// domain/result/DataResult.kt
sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
}

// Used in Repository — transforms response to domain model
fun <T, R> DataResult<T>.mapSuccess(transform: (T) -> R): DataResult<R> =
    when (this) {
        is DataResult.Success -> DataResult.Success(transform(data))
        is DataResult.Error   -> DataResult.Error(message)
    }

// Used in ViewModel — converts to UI state
fun <T> DataResult<T>.toUiState(): UiState<T> =
    when (this) {
        is DataResult.Success -> UiState.Success(data)
        is DataResult.Error   -> UiState.Error(message)
    }
