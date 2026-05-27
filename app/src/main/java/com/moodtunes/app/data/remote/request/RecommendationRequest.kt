package com.moodtunes.app.data.remote.request

data class RecommendationRequest(
    val mood: String,
    val customText: String?      = null,
    val genreIds: List<String>   = emptyList(),
    val count: Int               = 8,
    val includeSpotify: Boolean  = true,
    val includeYoutube: Boolean  = true,
)
