package com.moodtunes.app.domain.model

data class Session(
    val id: String,
    val mood: String,
    val customText: String?,
    val moodInterpretation: String?,
    val songCount: Int,
    val createdAt: String,
    val songs: List<Song>,
)
