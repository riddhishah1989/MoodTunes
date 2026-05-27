package com.moodtunes.app.domain.model

data class ShareResult(
    val shareUrl: String,
    val sessionId: String,
    val mood: String,
    val songCount: Int,
)
