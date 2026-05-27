package com.moodtunes.app.data.remote.response

// GET /api/v1/journal → ApiResponse<JournalResponse>
data class JournalResponse(
    val entries: List<JournalEntryResponse>,
    val totalCount: Int,
    val hasMore: Boolean,
)

// POST /api/v1/journal     → ApiResponse<JournalEntryResponse>
// GET  /api/v1/journal/:id → ApiResponse<JournalEntryResponse>
data class JournalEntryResponse(
    val id: String,
    val mood: String,
    val moodEmoji: String,
    val note: String?,
    val rating: Int,
    val tags: List<String>?,
    val createdAt: String,
)
