package com.moodtunes.app.data.mapper

import com.moodtunes.app.data.local.FavouriteEntity
import com.moodtunes.app.data.remote.response.ApiSong
import com.moodtunes.app.domain.model.Song

object Mapper {

    fun apiSongToSong(apiSong: ApiSong) = Song(
        id = "${apiSong.title}-${apiSong.artist}".hashCode().toString(),
        title = apiSong.title,
        artist = apiSong.artist,
        album = apiSong.album,
        genre = apiSong.genre,
        year = apiSong.year,
        reason = apiSong.reason,
        energyLevel = apiSong.energyLevel,
        tempo = apiSong.tempo,
        spotifyQuery = apiSong.spotifyQuery,
        youtubeQuery = apiSong.youtubeQuery,
        spotifyUrl = apiSong.spotify?.spotifyUrl,
        previewUrl = apiSong.spotify?.previewUrl,
        albumArt = apiSong.spotify?.albumArt,
        albumArtThumb = apiSong.spotify?.albumArtThumb,
        youtubeUrl = apiSong.youtube?.youtubeUrl,
        youtubeThumbnail = apiSong.youtube?.youtubeThumbnail,
        youtubeVideoId = apiSong.youtube?.youtubeVideoId,
    )

    fun songToFavouriteEntity(song: Song) = FavouriteEntity(
        songId = song.id,
        title = song.title,
        artist = song.artist,
        album = song.album,
        genre = song.genre,
        albumArt = song.albumArt,
        spotifyUrl = song.spotifyUrl,
        youtubeUrl = song.youtubeUrl,
    )

    fun favouriteEntityToSong(entity: FavouriteEntity) = Song(
        id = entity.songId,
        title = entity.title,
        artist = entity.artist,
        album = entity.album,
        genre = entity.genre,
        year = 0,
        reason = "",
        energyLevel = "",
        tempo = "",
        spotifyQuery = "",
        youtubeQuery = "",
        spotifyUrl = entity.spotifyUrl,
        previewUrl = null,
        albumArt = entity.albumArt,
        albumArtThumb = null,
        youtubeUrl = entity.youtubeUrl,
        youtubeThumbnail = null,
        youtubeVideoId = null,
        isFavourite = true,
    )
}
