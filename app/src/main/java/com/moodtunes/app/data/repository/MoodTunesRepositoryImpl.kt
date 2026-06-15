package com.moodtunes.app.data.repository

import com.moodtunes.app.data.mapper.toDomain
import com.moodtunes.app.data.remote.MoodTunesApiService
import com.moodtunes.app.data.remote.request.AddFavouriteRequest
import com.moodtunes.app.data.remote.request.AddJournalRequest
import com.moodtunes.app.data.remote.request.ChangePasswordRequest
import com.moodtunes.app.data.remote.request.DeleteAccountRequest
import com.moodtunes.app.data.remote.request.ForgotPasswordRequest
import com.moodtunes.app.data.remote.request.RecommendationRequest
import com.moodtunes.app.data.remote.request.ResendOTPRequest
import com.moodtunes.app.data.remote.request.ResetPasswordRequest
import com.moodtunes.app.data.remote.request.ShareRequest
import com.moodtunes.app.data.remote.request.SignInRequest
import com.moodtunes.app.data.remote.request.SignUpRequest
import com.moodtunes.app.data.remote.request.UpdateProfileRequest
import com.moodtunes.app.data.remote.request.VerifyOTPRequest
import com.moodtunes.app.data.remote.response.safeApiCall
import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.model.Favourite
import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.domain.model.Insights
import com.moodtunes.app.domain.model.JournalEntry
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.RecommendationResult
import com.moodtunes.app.domain.model.Session
import com.moodtunes.app.domain.model.ShareResult
import com.moodtunes.app.domain.model.User
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoodTunesRepositoryImpl @Inject constructor(private val api: MoodTunesApiService) :
    IMoodTunesRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        preferredGenres: List<String>,
    ): DataResult<Auth> = safeApiCall {
        api.signUp(
            SignUpRequest(
                name = name,
                email = email,
                password = password,
                preferredGenres = preferredGenres,
            )
        )
    }.mapSuccess { it.toDomain() }

    override suspend fun signIn(
        email: String,
        password: String,
    ): DataResult<Auth> = safeApiCall {
        api.signIn(SignInRequest(email = email, password = password))
    }.mapSuccess { it.toDomain() }

    override suspend fun getMe(): DataResult<User> = safeApiCall {
        api.getMe()
    }.mapSuccess { it.toDomain() }

    override suspend fun updateProfile(
        name: String,
        preferredGenres: List<String>,
    ): DataResult<User> = safeApiCall {
        api.updateProfile(
            UpdateProfileRequest(
                name = name,
                preferredGenres = preferredGenres,
            )
        )
    }.mapSuccess { it.user.toDomain() }

    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
    ): DataResult<Unit> = safeApiCall {
        api.changePassword(ChangePasswordRequest(currentPassword, newPassword))
    }.mapSuccess { }

    override suspend fun deleteAccount(
        password: String,
    ): DataResult<Unit> = safeApiCall {
        api.deleteAccount(DeleteAccountRequest(password))
    }.mapSuccess { }

    // ── OTP / Password Reset ──────────────────────────────────

    override suspend fun forgotPassword(
        email: String,
    ): DataResult<String> = safeApiCall {
        api.forgotPassword(ForgotPasswordRequest(email = email))
    }.mapSuccess { it.message }

    override suspend fun verifyOTP(
        email: String,
        otp: String,
    ): DataResult<String> = safeApiCall {
        api.verifyOTP(VerifyOTPRequest(email = email, otp = otp))
    }.mapSuccess { it.email }  // returns verified email → pass to ResetPassword screen

    override suspend fun resendOTP(
        email: String,
    ): DataResult<String> = safeApiCall {
        api.resendOTP(ResendOTPRequest(email = email))
    }.mapSuccess { it.message }

    override suspend fun resetPassword(
        email: String,
        newPassword: String,
        confirmPassword: String,
    ): DataResult<String> = safeApiCall {
        api.resetPassword(
            ResetPasswordRequest(
                email = email,
                newPassword = newPassword,
                confirmPassword = confirmPassword,
            )
        )
    }.mapSuccess { it.message }

    override suspend fun refreshToken(): DataResult<String> = safeApiCall {
        api.refreshToken()
    }.mapSuccess { it.token }

    // ── Moods ─────────────────────────────────────────────────

    override suspend fun getMoods(): DataResult<List<Mood>> = safeApiCall {
        api.getMoods()
    }.mapSuccess { it.toDomain() }

    // ── Genres ────────────────────────────────────────────────

    override suspend fun getGenres(): DataResult<List<Genre>> = safeApiCall {
        api.getGenres()
    }.mapSuccess { it.toDomain() }

    // ── Recommendations ───────────────────────────────────────

    override suspend fun getRecommendations(
        mood: String,
        customText: String?,
        genreIds: List<String>,
        count: Int,
        includeSpotify: Boolean,
        includeYoutube: Boolean,
    ): DataResult<RecommendationResult> = safeApiCall {
        api.getRecommendations(
            RecommendationRequest(
                mood = mood,
                customText = customText,
                genreIds = genreIds,
                count = count,
                includeSpotify = includeSpotify,
                includeYoutube = includeYoutube,
            )
        )
    }.mapSuccess { it.toDomain() }

        // ── Favourites ────────────────────────────────────────────

    override suspend fun getFavourites(
        limit: Int,
        offset: Int,
    ): DataResult<List<Favourite>> = safeApiCall {
        api.getFavourites(limit = limit, offset = offset)
    }.mapSuccess { it.toDomain() }

    override suspend fun addFavourite(
        songId: String,
        title: String,
        artist: String,
        album: String?,
        genre: String?,
        albumArt: String?,
        spotifyUrl: String?,
        youtubeUrl: String?,
    ): DataResult<String> = safeApiCall {
        api.addFavourite(
            AddFavouriteRequest(
                songId = songId,
                title = title,
                artist = artist,
                album = album,
                genre = genre,
                albumArt = albumArt,
                spotifyUrl = spotifyUrl,
                youtubeUrl = youtubeUrl,
            )
        )
    }.mapSuccess { it.savedAt }

    override suspend fun checkFavourite(
        songId: String,
    ): DataResult<Boolean> = safeApiCall {
        api.checkFavourite(songId)
    }.mapSuccess { it.isFavourite }

    override suspend fun removeFavourite(
        songId: String,
    ): DataResult<Unit> = safeApiCall {
        api.removeFavourite(songId)
    }.mapSuccess { }

    override suspend fun clearFavourites(): DataResult<Unit> {
        TODO("Not yet implemented")
    }

    // ── Journal ───────────────────────────────────────────────

    override suspend fun getJournal(
        limit: Int,
        offset: Int,
    ): DataResult<List<JournalEntry>> = safeApiCall {
        api.getJournal(limit = limit, offset = offset)
    }.mapSuccess { it.toDomain() }

    override suspend fun addJournalEntry(
        mood: String,
        rating: Int,
        note: String?,
        tags: List<String>,
    ): DataResult<JournalEntry> = safeApiCall {
        api.addJournalEntry(
            AddJournalRequest(
                mood = mood,
                rating = rating,
                note = note,
                tags = tags,
            )
        )
    }.mapSuccess { it.toDomain() }

    override suspend fun deleteJournalEntry(
        id: String,
    ): DataResult<Unit> = safeApiCall {
        api.deleteJournalEntry(id)
    }.mapSuccess { }

    override suspend fun clearJournal(): DataResult<Unit> {
        TODO("Not yet implemented")
    }


    // ── Insights ──────────────────────────────────────────────

    override suspend fun getInsights(
        days: Int,
    ): DataResult<Insights> = safeApiCall {
        api.getInsights(days)
    }.mapSuccess { it.toDomain() }

    // ── Share ─────────────────────────────────────────────────

    override suspend fun generateShareLink(
        sessionId: String,
    ): DataResult<ShareResult> = safeApiCall {
        api.generateShareLink(ShareRequest(sessionId))
    }.mapSuccess { it.toDomain() }
}

// ── Extension: DataResult<T> → DataResult<R> ─────────────────
// Transforms success data while passing errors through unchanged
fun <T, R> DataResult<T>.mapSuccess(transform: (T) -> R): DataResult<R> =
    when (this) {
        is DataResult.Success -> DataResult.Success(transform(data))
        is DataResult.Error -> DataResult.Error(message)
    }
