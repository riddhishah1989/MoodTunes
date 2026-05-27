package com.moodtunes.app.domain.repository

import com.moodtunes.app.data.remote.request.UpdateProfileRequest
import com.moodtunes.app.domain.model.*
import com.moodtunes.app.domain.result.DataResult

/**
 * Repository contract defined in domain layer.
 *
 * - UseCases depend on this interface (not the implementation)
 * - MoodTunesRepository in data/ implements this
 * - Hilt binds the implementation to this interface via RepositoryModule
 *
 * domain/ has zero dependency on data/ — only the other way around.
 */
interface IMoodTunesRepository {

    // ── Auth ──────────────────────────────────────────────────
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
    ): DataResult<Auth>

    suspend fun signIn(
        email: String,
        password: String,
    ): DataResult<Auth>

    suspend fun getMe(): DataResult<User>

    suspend fun updateProfile(
        request: UpdateProfileRequest,
    ): DataResult<User>

    suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
    ): DataResult<Unit>

    suspend fun deleteAccount(
        password: String,
    ): DataResult<Unit>

    // ── Moods ─────────────────────────────────────────────────
    suspend fun getMoods(): DataResult<List<Mood>>

    // ── Genres ────────────────────────────────────────────────
    suspend fun getGenres(): DataResult<List<Genre>>

    // ── Recommendations ───────────────────────────────────────
    suspend fun getRecommendations(
        mood: String,
        customText: String?,
        genreIds: List<String>,
        count: Int,
        includeSpotify: Boolean,
        includeYoutube: Boolean,
    ): DataResult<RecommendationResult>

    // ── History ───────────────────────────────────────────────
    suspend fun getHistory(
        limit: Int,
        offset: Int,
    ): DataResult<List<Session>>

    suspend fun getSession(
        id: String,
    ): DataResult<Session>

    suspend fun deleteSession(
        id: String,
    ): DataResult<Unit>

    suspend fun clearHistory(): DataResult<Unit>

    // ── Favourites ────────────────────────────────────────────
    suspend fun getFavourites(
        limit: Int,
        offset: Int,
    ): DataResult<List<Favourite>>

    suspend fun addFavourite(
        songId: String,
        title: String,
        artist: String,
        album: String?,
        genre: String?,
        albumArt: String?,
        spotifyUrl: String?,
        youtubeUrl: String?,
    ): DataResult<String>

    suspend fun checkFavourite(
        songId: String,
    ): DataResult<Boolean>

    suspend fun removeFavourite(
        songId: String,
    ): DataResult<Unit>

    suspend fun clearFavourites(): DataResult<Unit>

    // ── Journal ───────────────────────────────────────────────
    suspend fun getJournal(
        limit: Int,
        offset: Int,
    ): DataResult<List<JournalEntry>>

    suspend fun addJournalEntry(
        mood: String,
        rating: Int,
        note: String?,
        tags: List<String>,
    ): DataResult<JournalEntry>

    suspend fun deleteJournalEntry(
        id: String,
    ): DataResult<Unit>

    suspend fun clearJournal(): DataResult<Unit>

    // ── Insights ──────────────────────────────────────────────
    suspend fun getInsights(
        days: Int,
    ): DataResult<Insights>

    // ── Share ─────────────────────────────────────────────────
    suspend fun generateShareLink(
        sessionId: String,
    ): DataResult<ShareResult>
}