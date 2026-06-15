package com.moodtunes.app.data.remote.request

data class UpdateProfileRequest(
    val name: String? = null,
    val preferredGenres: List<String>? = null,
)
