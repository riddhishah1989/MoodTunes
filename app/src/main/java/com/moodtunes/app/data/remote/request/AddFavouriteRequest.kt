package com.moodtunes.app.data.remote.request

data class AddFavouriteRequest(
    val songId: String,
    val title: String,
    val artist: String,
    val album: String?      = null,
    val genre: String?      = null,
    val albumArt: String?   = null,
    val spotifyUrl: String? = null,
    val youtubeUrl: String? = null,
)
