package com.moodtunes.app.data.remote.response

data class MoodApiItem(
    val id: String,
    val label: String,
    val emoji: String,
    val color: String,
    val description: String,
)

data class MoodsData(
    val moods: List<MoodApiItem>,
    val count: Int,
)

data class MoodsResponse(
    val success: Boolean,
    val data: MoodsData?,
)
