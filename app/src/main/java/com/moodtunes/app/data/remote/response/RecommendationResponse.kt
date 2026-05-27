package com.moodtunes.app.data.remote.response

// POST /api/v1/recommendations → ApiResponse<RecommendationResponse>
data class RecommendationResponse(
    val sessionId: String,
    val moodInput: String,
    val moodInterpretation: String,
    val count: Int,
    val recommendations: List<SongResponse>,
)

data class SongResponse(
    val id: String,
    val title: String,
    val artist: String,
    val album: String?,
    val genre: String?,
    val year: Int?,
    val reason: String,
    val energyLevel: String?,
    val tempo: String?,
    val spotifyUrl: String?,
    val previewUrl: String?,
    val albumArt: String?,
    val albumArtThumb: String?,
    val youtubeUrl: String?,
    val youtubeThumbnail: String?,
    val youtubeTitle: String?,
)
