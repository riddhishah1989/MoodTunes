package com.moodtunes.app.data.remote.response

// GET /api/v1/spotify/search → ApiResponse<SpotifySearchResponse>
data class SpotifySearchResponse(
    val spotifyId: String?,
    val spotifyUrl: String?,
    val previewUrl: String?,
    val albumArt: String?,
    val albumArtThumb: String?,
    val title: String?,
    val artist: String?,
    val album: String?,
    val durationMs: Int?,
    val popularity: Int?,
    val explicit: Boolean?,
)
