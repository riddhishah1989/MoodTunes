package com.moodtunes.app.data.remote.response

// GET /api/v1/moods → ApiResponse<MoodsResponse>
data class MoodsResponse(
    val moods: List<MoodItemResponse>,
    val count: Int,
)

data class MoodItemResponse(
    val id: String,
    val label: String,
    val emoji: String,
    val description: String,
    val colorHex: String,
    val bgColorHex: String,
)
