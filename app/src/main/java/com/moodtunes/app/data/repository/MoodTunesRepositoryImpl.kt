package com.moodtunes.app.data.repository

import com.apollographql.apollo3.ApolloClient
import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.data.local.FavouriteDao
import com.moodtunes.app.data.local.MoodSessionDao
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.data.local.PreferencesManager
import com.moodtunes.app.data.mapper.Mapper
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.domain.repository.MoodTunesRepository
import com.moodtunes.app.graphql.GetRecommendationsMutation
import com.moodtunes.app.graphql.SignInMutation
import com.moodtunes.app.graphql.SignUpMutation
import com.moodtunes.app.graphql.type.RecommendationInput
import com.moodtunes.app.graphql.type.SignInInput
import com.moodtunes.app.graphql.type.SignUpInput
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoodTunesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val preferencesManager: PreferencesManager,
    private val sessionDao: MoodSessionDao,
    private val favouriteDao: FavouriteDao,
) : MoodTunesRepository {

    override suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
        val response = apolloClient.mutation(SignInMutation(SignInInput(email, password))).execute()
        if (response.hasErrors()) {
            throw Exception(response.errors?.firstOrNull()?.message ?: "Sign-in failed")
        }
        val token = response.data?.signIn?.token ?: throw Exception("No token returned")
        preferencesManager.jwtToken = token
        preferencesManager.isLoggedIn = true
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<Unit> = runCatching {
        val response = apolloClient.mutation(SignUpMutation(SignUpInput(name, email, password))).execute()
        if (response.hasErrors()) {
            throw Exception(response.errors?.firstOrNull()?.message ?: "Sign-up failed")
        }
        val token = response.data?.signUp?.token ?: throw Exception("No token returned")
        preferencesManager.jwtToken = token
        preferencesManager.isLoggedIn = true
    }

    override suspend fun signOut() {
        preferencesManager.jwtToken = null
        preferencesManager.isLoggedIn = false
    }

    override suspend fun getRecommendations(
        mood: Mood,
        customText: String?,
        count: Int,
    ): Result<Pair<String, List<Song>>> = runCatching {
        val input = RecommendationInput(
            mood = mood.label,
            customText = customText?.takeIf { it.isNotBlank() },
            count = count,
            includeSpotify = true,
            includeYoutube = true,
        )
        val response = apolloClient.mutation(GetRecommendationsMutation(input)).execute()
        if (response.hasErrors()) {
            throw Exception(response.errors?.firstOrNull()?.message ?: "Failed to get recommendations")
        }
        val session = response.data?.getRecommendations
            ?: throw Exception("No recommendations returned")
        val songs = session.songs.map { it.toDomain() }
        Pair(session.moodInterpretation ?: "", songs)
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

    private fun GetRecommendationsMutation.Song.toDomain() = Song(
        id = id,
        title = title,
        artist = artist,
        album = album ?: "",
        genre = genre ?: "",
        year = year ?: 0,
        reason = reason ?: "",
        energyLevel = energyLevel ?: "",
        tempo = tempo ?: "",
        spotifyQuery = spotifyQuery ?: "",
        youtubeQuery = youtubeQuery ?: "",
        spotifyUrl = spotify?.spotifyUrl,
        previewUrl = spotify?.previewUrl,
        albumArt = spotify?.albumArt,
        albumArtThumb = spotify?.albumArtThumb,
        youtubeUrl = youtube?.youtubeUrl,
        youtubeThumbnail = youtube?.youtubeThumbnail,
        youtubeVideoId = youtube?.youtubeVideoId,
    )
}
