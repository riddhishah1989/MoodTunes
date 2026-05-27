package com.moodtunes.app.domain.usecase.recommendation

import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.RecommendationResult
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        mood: String,
        customText: String? = null,
        genreIds: List<String> = emptyList(),
        count: Int = 8,
        includeSpotify: Boolean = true,
        includeYoutube: Boolean = true,
    ): DataResult<RecommendationResult> {
        // Business rule — sanitize custom text
        val sanitizedText = customText
            ?.trim()
            ?.takeIf { it.isNotEmpty() }

        // Business rule — mood must not be blank
        if (mood.isBlank()) {
            return DataResult.Error("Please select or describe your mood.")
        }

        // Business rule — count must be between 1 and 15
        val validCount = count.coerceIn(1, 15)

        // Business rule — max 5 genre filters
        val validGenreIds = genreIds.take(5)

        return repository.getRecommendations(
            mood = mood.trim(),
            customText = sanitizedText,
            genreIds = validGenreIds,
            count = validCount,
            includeSpotify = includeSpotify,
            includeYoutube = includeYoutube,
        )
    }
}

// ─────────────────────────────────────────────────────────────

class GetMoodsUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(): DataResult<List<Mood>> =
        repository.getMoods()
}

// ─────────────────────────────────────────────────────────────

class GetGenresUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(): DataResult<List<Genre>> =
        repository.getGenres()
}
