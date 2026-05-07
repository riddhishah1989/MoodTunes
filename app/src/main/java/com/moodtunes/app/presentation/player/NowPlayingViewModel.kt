package com.moodtunes.app.presentation.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.domain.repository.MoodTunesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val repository: MoodTunesRepository,
) : ViewModel() {
    private val _song = MutableStateFlow<Song?>(null)
    val song: StateFlow<Song?> = _song.asStateFlow()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    fun load(songIndex: Int, sessionId: String) {
        viewModelScope.launch {
            val session = repository.getSession(sessionId) ?: return@launch
            _songs.value = session.songs
            _currentIndex.value = songIndex.coerceIn(0, session.songs.lastIndex)
            _song.value = session.songs.getOrNull(_currentIndex.value)
        }
    }

    fun next() {
        val songs = _songs.value; if (songs.isEmpty()) return
        _currentIndex.value = (_currentIndex.value + 1) % songs.size
        _song.value = songs[_currentIndex.value]
    }

    fun previous() {
        val songs = _songs.value; if (songs.isEmpty()) return
        _currentIndex.value = if (_currentIndex.value == 0) songs.lastIndex else _currentIndex.value - 1
        _song.value = songs[_currentIndex.value]
    }

    fun isFavourite(songId: String) = repository.isFavourite(songId)
    fun toggleFavourite(song: Song) = viewModelScope.launch { repository.toggleFavourite(song) }
}
