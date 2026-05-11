package com.moodtunes.app.domain.model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val year: Int,
    val reason: String,
    val energyLevel: String,
    val tempo: String,
    val spotifyQuery: String,
    val youtubeQuery: String,
    val spotifyUrl: String?,
    val previewUrl: String?,
    val albumArt: String?,
    val albumArtThumb: String?,
    val youtubeUrl: String?,
    val youtubeThumbnail: String?,
    val youtubeVideoId: String?,
    var isFavourite: Boolean = false,
)
