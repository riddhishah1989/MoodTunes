package com.moodtunes.app.data.remote.response

// GET /api/v1/insights → ApiResponse<InsightsResponse>
data class InsightsResponse(
    val hasData: Boolean,
    val periodDays: Int,
    val mostCommonMood: MoodStatResponse?,
    val moodBreakdown: List<MoodStatResponse>?,
    val topGenres: List<GenreStatResponse>?,
    val genrePerMood: List<GenrePerMoodResponse>?,
    val totalSessions: Int,
    val totalJournalEntries: Int,
    val aiInsight: String?,
    val message: String?,
)

data class MoodStatResponse(
    val mood: String,
    val emoji: String,
    val count: Int,
    val avgRating: Float?,
)

data class GenreStatResponse(
    val genre: String,
    val count: Int,
)

data class GenrePerMoodResponse(
    val mood: String,
    val topGenre: String?,
)
