package com.moodtunes.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.domain.repository.MoodTunesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoodTunesRepository,
) : ViewModel() {
    val recentSessions: StateFlow<List<MoodSessionEntity>> =
        repository.getAllSessions()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
