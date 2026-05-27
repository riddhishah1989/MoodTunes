package com.moodtunes.app.data.remote.response

// GET /api/v1/youtube/search → ApiResponse<YouTubeSearchResponse>
data class YouTubeSearchResponse(
    val youtubeVideoId: String?,
    val youtubeUrl: String?,
    val youtubeThumbnail: String?,
    val youtubeTitle: String?,
    val channelName: String?,
)
