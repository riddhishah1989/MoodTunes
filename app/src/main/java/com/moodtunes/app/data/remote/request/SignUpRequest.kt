package com.moodtunes.app.data.remote.request

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val preferredGenres: List<String> = emptyList(),
)
