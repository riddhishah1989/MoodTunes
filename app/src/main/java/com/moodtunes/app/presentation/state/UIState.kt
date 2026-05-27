package com.moodtunes.app.presentation.state

/**
 * Generic UI state used across ALL screens in MoodTunes.
 *
 * Every ViewModel exposes a StateFlow<UiState<T>>
 * Every Composable observes and reacts to it.
 *
 * T = the data type for that screen e.g.
 *   UiState<RecommendationResult>
 *   UiState<List<Session>>
 *   UiState<Insights>
 *   UiState<User>
 */
sealed class UiState<out T> {

    /** Initial state — nothing has happened yet */
    object Idle : UiState<Nothing>()

    /** API call in progress — show loading indicator */
    object Loading : UiState<Nothing>()

    /** API call succeeded — T is the domain model to show */
    data class Success<T>(val data: T) : UiState<T>()

    /** API call failed — show error message + retry button */
    data class Error(val message: String) : UiState<Nothing>()
}

/**
 * Extension properties for easy state checks in Composables
 */
val <T> UiState<T>.isLoading get() = this is UiState.Loading
val <T> UiState<T>.isSuccess get() = this is UiState.Success
val <T> UiState<T>.isError   get() = this is UiState.Error
val <T> UiState<T>.isIdle    get() = this is UiState.Idle

/** Safely extract data — returns null if not Success */
fun <T> UiState<T>.dataOrNull(): T? = (this as? UiState.Success)?.data

/** Safely extract error — returns null if not Error */
fun UiState<*>.errorOrNull(): String? = (this as? UiState.Error)?.message
