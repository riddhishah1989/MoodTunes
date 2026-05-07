package com.moodtunes.app.domain.repository

import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface MoodTunesRepository {
    suspend fun getRecommendations(mood: Mood, customText: String?, count: Int = 8): Result<Pair<String, List<Song>>>
    fun getAllSessions(): Flow<List<MoodSessionEntity>>
    suspend fun getSession(id: String): MoodSessionEntity?
    suspend fun saveSession(mood: Mood, customText: String?, interpretation: String, songs: List<Song>): String
    suspend fun clearHistory()
    fun getAllFavourites(): Flow<List<FavouriteEntity>>
    fun isFavourite(songId: String): Flow<Boolean>
    suspend fun toggleFavourite(song: Song)
    suspend fun removeFavourite(songId: String)
    suspend fun clearFavourites()
}
