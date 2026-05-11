package com.moodtunes.app.data.remote.response

data class ApiSong(
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val year: Int,
    val durationSeconds: Int?,
    val reason: String,
    val energyLevel: String,
    val tempo: String,
    val spotifyQuery: String,
    val youtubeQuery: String,
    val spotify: SpotifyData?,
    val youtube: YouTubeData?,
)
