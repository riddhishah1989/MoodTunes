package com.moodtunes.app.data.remote.response

// GET /api/v1/genres → ApiResponse<GenresResponse>
data class GenresResponse(
    val genres: List<Genre>,
    val count: Int,
)

data class Genre(
    val id: String,
    val name: String,
    val description: String?,
    val emoji: String?,
)
