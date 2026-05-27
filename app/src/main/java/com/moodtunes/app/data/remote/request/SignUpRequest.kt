package com.moodtunes.app.data.remote.request

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: String?       = null,
    val birthDay: Int?                = null,
    val birthMonth: Int?              = null,
    val gender: String?               = null,
    val country: String?              = null,
    val preferredGenres: List<String> = emptyList(),
)
