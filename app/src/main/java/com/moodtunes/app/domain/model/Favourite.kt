package com.moodtunes.app.domain.model

data class Favourite(
    val id: String,
    val songId: String,
    val title: String,
    val artist: String?,
    val album: String?,
    val genre: String?,
    val albumArt: String?,
    val spotifyUrl: String?,
    val youtubeUrl: String?,
    val savedAt: String,
)
