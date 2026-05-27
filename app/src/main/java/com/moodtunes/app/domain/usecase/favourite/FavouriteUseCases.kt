package com.moodtunes.app.domain.usecase.favourite

import com.moodtunes.app.domain.model.Favourite
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import com.moodtunes.app.domain.result.DataResult
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        limit: Int  = 50,
        offset: Int = 0,
    ): DataResult<List<Favourite>> = repository.getFavourites(limit, offset)
}

// ─────────────────────────────────────────────────────────────

class AddFavouriteUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        songId: String,
        title: String,
        artist: String,
        album: String?      = null,
        genre: String?      = null,
        albumArt: String?   = null,
        spotifyUrl: String? = null,
        youtubeUrl: String? = null,
    ): DataResult<String> {
        // Business rule — songId, title, artist are required
        if (songId.isBlank()) return DataResult.Error("Song ID is required.")
        if (title.isBlank())  return DataResult.Error("Song title is required.")
        if (artist.isBlank()) return DataResult.Error("Artist name is required.")

        return repository.addFavourite(
            songId, title, artist, album, genre, albumArt, spotifyUrl, youtubeUrl
        )
    }
}

// ─────────────────────────────────────────────────────────────

class RemoveFavouriteUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        songId: String,
    ): DataResult<Unit> = repository.removeFavourite(songId)
}

// ─────────────────────────────────────────────────────────────

class CheckFavouriteUseCase @Inject constructor(
    private val repository: IMoodTunesRepository,
) {
    suspend operator fun invoke(
        songId: String,
    ): DataResult<Boolean> = repository.checkFavourite(songId)
}
