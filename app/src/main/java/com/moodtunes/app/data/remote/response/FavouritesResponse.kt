package com.moodtunes.app.data.remote.response

// GET /api/v1/favourites → ApiResponse<FavouritesResponse>
data class FavouritesResponse(
    val favourites: List<FavouriteItemResponse>,
    val count: Int,
    val total: Int,
    val hasMore: Boolean,
)

// POST /api/v1/favourites → ApiResponse<AddFavouriteResponse>
data class AddFavouriteResponse(
    val id: String,
    val songId: String,
    val title: String,
    val savedAt: String,
)

// GET /api/v1/favourites/:songId/check → ApiResponse<CheckFavouriteResponse>
data class CheckFavouriteResponse(
    val isFavourite: Boolean,
    val songId: String,
)

data class FavouriteItemResponse(
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
