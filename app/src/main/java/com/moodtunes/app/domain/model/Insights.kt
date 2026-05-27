package com.moodtunes.app.domain.model

data class Insights(
    val hasData: Boolean,
    val periodDays: Int,
    val mostCommonMood: MoodStat?,
    val moodBreakdown: List<MoodStat>,
    val topGenres: List<GenreStat>,
    val genrePerMood: List<GenrePerMood>,
    val totalSessions: Int,
    val totalJournalEntries: Int,
    val aiInsight: String?,
    val message: String?,
)

data class MoodStat(
    val mood: String,
    val emoji: String,
    val count: Int,
    val avgRating: Float?,
)

data class GenreStat(
    val genre: String,
    val count: Int,
)

data class GenrePerMood(
    val mood: String,
    val topGenre: String?,
)
