package com.moodtunes.app.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.domain.result.DataResult
import com.moodtunes.app.domain.result.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel — all MoodTunes ViewModels extend this.
 *
 * Provides:
 *   - _uiState: MutableStateFlow<UiState<T>>
 *   - uiState:  StateFlow<UiState<T>> exposed to Composables
 *   - launch()  helper that auto-sets Loading → Success/Error
 */
abstract class BaseViewModel : ViewModel() {

    protected fun launch(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
                // optional — add crash reporting here later
                // e.g. FirebaseCrashlytics.getInstance().recordException(e)
            }
        }
    }
}
