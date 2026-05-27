package com.moodtunes.app.domain.model

data class Mood(
    val id: String,
    val label: String,
    val emoji: String,
    val description: String,
    val colorHex: String,
    val bgColorHex: String,
)
