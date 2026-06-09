package com.moodtunes.app.data.remote.response

data class MoodsResponse(
    val moods: List<Mood>,
    val count: Int,
)

data class Mood(
    val id: String,
    val label: String,
    val emoji: String,
    val description: String,
    val colorHex: String,
    val bgColorHex: String,
)
