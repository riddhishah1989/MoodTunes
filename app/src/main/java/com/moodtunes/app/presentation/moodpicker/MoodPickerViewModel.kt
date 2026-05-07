package com.moodtunes.app.presentation.moodpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.PresetMoods
import com.moodtunes.app.domain.repository.MoodTunesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MoodPickerUiState {
    object Idle : MoodPickerUiState()
    object Loading : MoodPickerUiState()
    data class Success(val sessionId: String) : MoodPickerUiState()
    data class Error(val message: String) : MoodPickerUiState()
}

@HiltViewModel
class MoodPickerViewModel @Inject constructor(
    private val repository: MoodTunesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MoodPickerUiState>(MoodPickerUiState.Idle)
    val uiState: StateFlow<MoodPickerUiState> = _uiState.asStateFlow()

    private val _selectedMood = MutableStateFlow<Mood?>(null)
    val selectedMood: StateFlow<Mood?> = _selectedMood.asStateFlow()

    val moods = PresetMoods.all

    fun selectMood(mood: Mood) { _selectedMood.value = mood }

    fun getRecommendations(customText: String) {
        val mood = _selectedMood.value ?: return
        viewModelScope.launch {
            _uiState.value = MoodPickerUiState.Loading
            repository.getRecommendations(mood, customText.takeIf { it.isNotBlank() })
                .onSuccess { (interpretation, songs) ->
                    val sessionId = repository.saveSession(mood, customText, interpretation, songs)
                    _uiState.value = MoodPickerUiState.Success(sessionId)
                }
                .onFailure { _uiState.value = MoodPickerUiState.Error(it.message ?: "Something went wrong") }
        }
    }

    fun resetState() { _uiState.value = MoodPickerUiState.Idle }
}
