package com.moodtunes.app.data.remote.response

data class SpotifyData(
    val spotifyId: String?,
    val spotifyUrl: String?,
    val previewUrl: String?,
    val albumArt: String?,
    val albumArtThumb: String?,
    val durationMs: Int?,
    val popularity: Int?,
    val explicit: Boolean?,
)

data class YouTubeData(
    val youtubeVideoId: String?,
    val youtubeUrl: String?,
    val youtubeThumbnail: String?,
    val youtubeTitle: String?,
)
