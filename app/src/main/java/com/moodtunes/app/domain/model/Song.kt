package com.moodtunes.app.domain.model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String?,
    val genre: String?,
    val year: Int?,
    val reason: String,
    val energyLevel: String?,
    val tempo: String?,
    val albumArt: String?,
    val albumArtThumb: String?,
    val previewUrl: String?,
    val spotifyUrl: String?,
    val youtubeUrl: String?,
    val youtubeThumbnail: String?,
    val isFavourite: Boolean = false,
)
