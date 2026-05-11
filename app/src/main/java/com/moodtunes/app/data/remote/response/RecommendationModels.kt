package com.moodtunes.app.data.remote.response

data class RecommendationRequest(
    val mood: String,
    val customText: String?,
    val count: Int = 8,
    val includeSpotify: Boolean = true,
    val includeYoutube: Boolean = true,
)

data class RecommendationData(
    val moodInput: String,
    val moodInterpretation: String,
    val count: Int,
    val recommendations: List<ApiSong>,
)

data class RecommendationResponse(
    val success: Boolean,
    val data: RecommendationData?,
    val error: String?,
)
