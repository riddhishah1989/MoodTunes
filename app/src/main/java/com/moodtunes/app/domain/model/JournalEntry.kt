package com.moodtunes.app.domain.model

data class JournalEntry(
    val id: String,
    val mood: String,
    val moodEmoji: String,
    val note: String?,
    val rating: Int,
    val tags: List<String>,
    val createdAt: String,
)
