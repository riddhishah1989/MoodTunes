package com.moodtunes.app.domain.model

data class RecommendationResult(
    val sessionId: String,
    val moodInput: String,
    val moodInterpretation: String,
    val songs: List<Song>,
)
