package com.moodtunes.app.data.remote.response

// POST /api/v1/share → ApiResponse<ShareResponse>
data class ShareResponse(
    val shareUrl: String,
    val sessionId: String,
    val mood: String,
    val songCount: Int,
    val message: String?,
)

// GET /api/v1/share/:id → ApiResponse<SharedPlaylistResponse>
data class SharedPlaylistResponse(
    val shareUrl: String,
    val mood: String,
    val moodInterpretation: String?,
    val songCount: Int,
    val createdAt: String,
    val songs: List<SongResponse>,
)
