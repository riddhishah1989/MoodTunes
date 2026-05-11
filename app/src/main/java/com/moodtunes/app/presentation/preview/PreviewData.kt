package com.moodtunes.app.presentation.preview

import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.data.local.MoodSessionEntity
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.Song

internal val previewMood = Mood(
    id = "happy", label = "Happy", emoji = "😊", description = "Upbeat & joyful",
    color = 0xFFFFD93D, bgColor = 0xFF1A1500,
)

internal val previewSong = Song(
    id = "1", title = "Blinding Lights", artist = "The Weeknd", album = "After Hours",
    genre = "Pop", year = 2020, reason = "Energetic beat matching your mood",
    energyLevel = "High", tempo = "Fast",
    spotifyQuery = "Blinding Lights The Weeknd", youtubeQuery = "Blinding Lights The Weeknd",
    spotifyUrl = null, previewUrl = null, albumArt = null, albumArtThumb = null,
    youtubeUrl = null, youtubeThumbnail = null, youtubeVideoId = null,
)

internal val previewSession = MoodSessionEntity(
    sessionId = "1", moodId = "happy", moodLabel = "Happy", moodEmoji = "😊",
    customText = null, moodInterpretation = "Feeling joyful today",
    songs = emptyList(), timestamp = System.currentTimeMillis(),
)

internal val previewHistorySession = MoodSessionEntity(
    sessionId = "2", moodId = "happy", moodLabel = "Happy", moodEmoji = "😊",
    customText = null, moodInterpretation = "Feeling great today",
    songs = emptyList(), timestamp = System.currentTimeMillis(),
)

internal val previewFavourite = FavouriteEntity(
    songId = "1",
    title = "Blinding Lights",
    artist = "The Weeknd",
    album = "After Hours",
    genre = "Pop",
    albumArt = null,
    spotifyUrl = null,
    youtubeUrl = null,
)
