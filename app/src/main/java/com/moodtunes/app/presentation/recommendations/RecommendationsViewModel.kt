package com.moodtunes.app.presentation.recommendations

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.domain.repository.MoodTunesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: MoodTunesRepository,
) : ViewModel() {

    private val _session = MutableStateFlow<MoodSessionEntity?>(null)
    val session: StateFlow<MoodSessionEntity?> = _session.asStateFlow()

    fun loadSession(sessionId: String) {
        viewModelScope.launch {
            _session.value = repository.getSession(sessionId)
        }
    }

    fun toggleFavourite(song: Song) {
        viewModelScope.launch {
            repository.toggleFavourite(song)
        }
    }

    fun getFavouriteFlow(songId: String) = repository.isFavourite(songId)
}
