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
abstract class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<T>>(UiState.Idle)
    val uiState: StateFlow<UiState<T>> = _uiState.asStateFlow()

    /**
     * Launches a suspend block, automatically manages state:
     *   1. Sets Loading
     *   2. Runs the block
     *   3. Sets Success or Error from DataResult
     *
     * Usage in ViewModel:
     *   fun load() = launch { repository.getSomething() }
     */
    protected fun launch(block: suspend () -> DataResult<T>) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = block().toUiState()
        }
    }

    /** Reset back to Idle — useful after navigating away */
    fun resetState() { _uiState.value = UiState.Idle }
}
