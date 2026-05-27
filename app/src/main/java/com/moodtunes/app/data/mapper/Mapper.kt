package com.moodtunes.app.data.mapper

import com.moodtunes.app.data.remote.response.*
import com.moodtunes.app.domain.model.*

// ═══════════════════════════════════════════════════════════════
// Mappers — Response → Domain
// Called inside Repository before returning to ViewModel
// ViewModel and UI only ever see Domain models — never Response
// ═══════════════════════════════════════════════════════════════

// ── User ──────────────────────────────────────────────────────
fun UserResponse.toDomain() = User(
    id              = id,
    name            = name,
    email           = email,
    profilePicture  = profilePicture,
    birthDay        = birthDay,
    birthMonth      = birthMonth,
    gender          = gender,
    country         = country,
    preferredGenres = preferredGenres ?: emptyList(),
    isVerified      = isVerified,
    lastLogin       = lastLogin,
    createdAt       = createdAt,
)

// ── Auth (signup + signin) ────────────────────────────────────
fun SignUpResponse.toDomain() = Auth(
    token   = token,
    user    = user.toDomain(),
    message = message,
)

fun SignInResponse.toDomain() = Auth(
    token   = token,
    user    = user.toDomain(),
    message = message,
)

// ── Mood ──────────────────────────────────────────────────────
fun MoodItemResponse.toDomain() = Mood(
    id          = id,
    label       = label,
    emoji       = emoji,
    description = description,
    colorHex    = colorHex,
    bgColorHex  = bgColorHex,
)

fun MoodsResponse.toDomain(): List<Mood> = moods.map { it.toDomain() }

// ── Genre ─────────────────────────────────────────────────────
fun GenreItemResponse.toDomain() = Genre(
    id          = id,
    name        = name,
    description = description,
    emoji       = emoji,
)

fun GenresResponse.toDomain(): List<Genre> = genres.map { it.toDomain() }

// ── Song ──────────────────────────────────────────────────────
fun SongResponse.toDomain() = Song(
    id               = id,
    title            = title,
    artist           = artist,
    album            = album,
    genre            = genre,
    year             = year,
    reason           = reason,
    energyLevel      = energyLevel,
    tempo            = tempo,
    albumArt         = albumArt,
    albumArtThumb    = albumArtThumb,
    previewUrl       = previewUrl,
    spotifyUrl       = spotifyUrl,
    youtubeUrl       = youtubeUrl,
    youtubeThumbnail = youtubeThumbnail,
)

// ── Recommendation ────────────────────────────────────────────
fun RecommendationResponse.toDomain() = RecommendationResult(
    sessionId          = sessionId,
    moodInput          = moodInput,
    moodInterpretation = moodInterpretation,
    songs              = recommendations.map { it.toDomain() },
)

// ── Session ───────────────────────────────────────────────────
fun SessionResponse.toDomain() = Session(
    id                 = id,
    mood               = mood,
    customText         = customText,
    moodInterpretation = moodInterpretation,
    songCount          = songCount,
    createdAt          = createdAt,
    songs              = songs?.map { it.toDomain() } ?: emptyList(),
)

fun HistoryResponse.toDomain(): List<Session> = sessions.map { it.toDomain() }

// ── Favourite ─────────────────────────────────────────────────
fun FavouriteItemResponse.toDomain() = Favourite(
    id         = id,
    songId     = songId,
    title      = title,
    artist     = artist,
    album      = album,
    genre      = genre,
    albumArt   = albumArt,
    spotifyUrl = spotifyUrl,
    youtubeUrl = youtubeUrl,
    savedAt    = savedAt,
)

fun FavouritesResponse.toDomain(): List<Favourite> = favourites.map { it.toDomain() }

// ── Journal ───────────────────────────────────────────────────
fun JournalEntryResponse.toDomain() = JournalEntry(
    id        = id,
    mood      = mood,
    moodEmoji = moodEmoji,
    note      = note,
    rating    = rating,
    tags      = tags ?: emptyList(),
    createdAt = createdAt,
)

fun JournalResponse.toDomain(): List<JournalEntry> = entries.map { it.toDomain() }

// ── Insights ──────────────────────────────────────────────────
fun MoodStatResponse.toDomain() = MoodStat(
    mood      = mood,
    emoji     = emoji,
    count     = count,
    avgRating = avgRating,
)

fun GenreStatResponse.toDomain() = GenreStat(
    genre = genre,
    count = count,
)

fun GenrePerMoodResponse.toDomain() = GenrePerMood(
    mood     = mood,
    topGenre = topGenre,
)

fun InsightsResponse.toDomain() = Insights(
    hasData             = hasData,
    periodDays          = periodDays,
    mostCommonMood      = mostCommonMood?.toDomain(),
    moodBreakdown       = moodBreakdown?.map { it.toDomain() } ?: emptyList(),
    topGenres           = topGenres?.map { it.toDomain() } ?: emptyList(),
    genrePerMood        = genrePerMood?.map { it.toDomain() } ?: emptyList(),
    totalSessions       = totalSessions,
    totalJournalEntries = totalJournalEntries,
    aiInsight           = aiInsight,
    message             = message,
)

// ── Share ─────────────────────────────────────────────────────
fun ShareResponse.toDomain() = ShareResult(
    shareUrl  = shareUrl,
    sessionId = sessionId,
    mood      = mood,
    songCount = songCount,
)
