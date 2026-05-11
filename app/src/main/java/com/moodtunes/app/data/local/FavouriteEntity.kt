package com.moodtunes.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val songId: String,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val albumArt: String?,
    val spotifyUrl: String?,
    val youtubeUrl: String?,
    val savedAt: Long = System.currentTimeMillis(),
)
