package com.moodtunes.app.data.repository

import com.moodtunes.app.data.local.FavouriteDao
import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.data.local.MoodSessionDao
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.data.mapper.Mapper
import com.moodtunes.app.data.remote.MoodTunesApiService
import com.moodtunes.app.data.remote.response.RecommendationRequest
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.domain.repository.MoodTunesRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoodTunesRepositoryImpl @Inject constructor(
    private val api: MoodTunesApiService,
    private val sessionDao: MoodSessionDao,
    private val favouriteDao: FavouriteDao,
) : MoodTunesRepository {

    override suspend fun getRecommendations(
        mood: Mood,
        customText: String?,
        count: Int,
    ): Result<Pair<String, List<Song>>> = runCatching {
        val response = api.getRecommendations(
            RecommendationRequest(
                mood = mood.label,
                customText = customText?.takeIf { it.isNotBlank() },
                count = count,
            )
        )
        if (!response.isSuccessful) {
            throw Exception("API error ${response.code()}: ${response.errorBody()?.string()}")
        }
        val body = response.body() ?: throw Exception("Empty response")
        if (!body.success || body.data == null) {
            throw Exception(body.error ?: "Unknown error from server")
        }
        val songs = body.data.recommendations.map { Mapper.apiSongToSong(it) }
        Pair(body.data.moodInterpretation, songs)
    }

    override fun getAllSessions(): Flow<List<MoodSessionEntity>> = sessionDao.getAllSessions()

    override suspend fun getSession(id: String): MoodSessionEntity? = sessionDao.getSession(id)

    override suspend fun saveSession(
        mood: Mood,
        customText: String?,
        interpretation: String,
        songs: List<Song>,
    ): String {
        val sessionId = UUID.randomUUID().toString()
        sessionDao.insertSession(
            MoodSessionEntity(
                sessionId = sessionId,
                moodId = mood.id,
                moodLabel = mood.label,
                moodEmoji = mood.emoji,
                customText = customText,
                moodInterpretation = interpretation,
                songs = songs,
                timestamp = System.currentTimeMillis(),
            )
        )
        return sessionId
    }

    override suspend fun clearHistory() = sessionDao.clearAll()

    override fun getAllFavourites(): Flow<List<FavouriteEntity>> = favouriteDao.getAllFavourites()

    override fun isFavourite(songId: String): Flow<Boolean> = favouriteDao.isFavourite(songId)

    override suspend fun toggleFavourite(song: Song) {
        favouriteDao.insertFavourite(Mapper.songToFavouriteEntity(song))
    }

    override suspend fun removeFavourite(songId: String) = favouriteDao.removeFavourite(songId)

    override suspend fun clearFavourites() = favouriteDao.clearAll()
}
