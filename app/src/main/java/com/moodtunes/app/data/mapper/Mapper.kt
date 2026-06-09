package com.moodtunes.app.data.mapper

import com.moodtunes.app.data.remote.response.FavouriteItemResponse
import com.moodtunes.app.data.remote.response.FavouritesResponse
import com.moodtunes.app.data.remote.response.GenrePerMoodResponse
import com.moodtunes.app.data.remote.response.GenreStatResponse
import com.moodtunes.app.data.remote.response.GenresResponse
import com.moodtunes.app.data.remote.response.InsightsResponse
import com.moodtunes.app.data.remote.response.JournalEntryResponse
import com.moodtunes.app.data.remote.response.JournalResponse
import com.moodtunes.app.data.remote.response.MoodStatResponse
import com.moodtunes.app.data.remote.response.MoodsResponse
import com.moodtunes.app.data.remote.response.RecommendationResponse
import com.moodtunes.app.data.remote.response.ShareResponse
import com.moodtunes.app.data.remote.response.SignInResponse
import com.moodtunes.app.data.remote.response.SignUpResponse
import com.moodtunes.app.data.remote.response.SongResponse
import com.moodtunes.app.data.remote.response.UserResponse
import com.moodtunes.app.domain.model.Auth
import com.moodtunes.app.domain.model.Favourite
import com.moodtunes.app.domain.model.Genre
import com.moodtunes.app.domain.model.GenrePerMood
import com.moodtunes.app.domain.model.GenreStat
import com.moodtunes.app.domain.model.Insights
import com.moodtunes.app.domain.model.JournalEntry
import com.moodtunes.app.domain.model.Mood
import com.moodtunes.app.domain.model.MoodStat
import com.moodtunes.app.domain.model.RecommendationResult
import com.moodtunes.app.domain.model.ShareResult
import com.moodtunes.app.domain.model.Song
import com.moodtunes.app.domain.model.User
import com.moodtunes.app.data.remote.response.Genre as GenreResponse
import com.moodtunes.app.data.remote.response.Mood as MoodResponse

fun UserResponse.toDomain(): User = User(
    id = id,
    name = name,
    email = email,
    preferredGenres = preferredGenres.orEmpty(),
    isVerified = isVerified,
    lastLogin = lastLogin,
    createdAt = createdAt,
)

fun SignUpResponse.toDomain(): Auth = Auth(
    token = token,
    user = user.toDomain(),
    message = message,
)

fun SignInResponse.toDomain(): Auth = Auth(
    token = token,
    user = user.toDomain(),
    message = message,
)

fun MoodResponse.toDomain(): Mood = Mood(
    id = id,
    label = label,
    emoji = emoji,
    description = description,
    colorHex = colorHex,
    bgColorHex = bgColorHex,
)

fun MoodsResponse.toDomain(): List<Mood> = moods.map { it.toDomain() }

fun GenreResponse.toDomain(): Genre = Genre(
    id = id,
    name = name,
    description = description,
    emoji = emoji,
)

fun GenresResponse.toDomain(): List<Genre> = genres.map { it.toDomain() }

fun SongResponse.toDomain(): Song = Song(
    id = id,
    title = title,
    artist = artist,
    album = album,
    genre = genre,
    year = year,
    reason = reason,
    energyLevel = energyLevel,
    tempo = tempo,
    albumArt = albumArt,
    albumArtThumb = albumArtThumb,
    previewUrl = previewUrl,
    spotifyUrl = spotifyUrl,
    youtubeUrl = youtubeUrl,
    youtubeThumbnail = youtubeThumbnail,
)

fun RecommendationResponse.toDomain(): RecommendationResult = RecommendationResult(
    sessionId = sessionId,
    moodInput = moodInput,
    moodInterpretation = moodInterpretation,
    songs = recommendations.map { it.toDomain() },
)
fun FavouriteItemResponse.toDomain(): Favourite = Favourite(
    id = id,
    songId = songId,
    title = title,
    artist = artist,
    album = album,
    genre = genre,
    albumArt = albumArt,
    spotifyUrl = spotifyUrl,
    youtubeUrl = youtubeUrl,
    savedAt = savedAt,
)

fun FavouritesResponse.toDomain(): List<Favourite> = favourites.map { it.toDomain() }

fun JournalEntryResponse.toDomain(): JournalEntry = JournalEntry(
    id = id,
    mood = mood,
    moodEmoji = moodEmoji,
    note = note,
    rating = rating,
    tags = tags.orEmpty(),
    createdAt = createdAt,
)

fun JournalResponse.toDomain(): List<JournalEntry> = entries.map { it.toDomain() }

fun MoodStatResponse.toDomain(): MoodStat = MoodStat(
    mood = mood,
    emoji = emoji,
    count = count,
    avgRating = avgRating,
)

fun GenreStatResponse.toDomain(): GenreStat = GenreStat(
    genre = genre,
    count = count,
)

fun GenrePerMoodResponse.toDomain(): GenrePerMood = GenrePerMood(
    mood = mood,
    topGenre = topGenre,
)

fun InsightsResponse.toDomain(): Insights = Insights(
    hasData = hasData,
    periodDays = periodDays,
    mostCommonMood = mostCommonMood?.toDomain(),
    moodBreakdown = moodBreakdown.orEmpty().map { it.toDomain() },
    topGenres = topGenres.orEmpty().map { it.toDomain() },
    genrePerMood = genrePerMood.orEmpty().map { it.toDomain() },
    totalSessions = totalSessions,
    totalJournalEntries = totalJournalEntries,
    aiInsight = aiInsight,
    message = message,
)

fun ShareResponse.toDomain(): ShareResult = ShareResult(
    shareUrl = shareUrl,
    sessionId = sessionId,
    mood = mood,
    songCount = songCount,
)
