package com.moodtunes.app.data.remote.response

// GET /api/v1/history → ApiResponse<HistoryResponse>
data class HistoryResponse(
    val sessions: List<SessionResponse>,
    val totalCount: Int,
    val hasMore: Boolean,
)

// GET /api/v1/history/:id → ApiResponse<SessionResponse>
data class SessionResponse(
    val id: String,
    val mood: String,
    val customText: String?,
    val moodInterpretation: String?,
    val songCount: Int,
    val createdAt: String,
    val songs: List<SongResponse>?,
)
