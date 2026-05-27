package com.moodtunes.app.data.remote.request

data class UpdateProfileRequest(
    val name: String?                  = null,
    val profilePicture: String?        = null,
    val birthDay: Int?                 = null,
    val birthMonth: Int?               = null,
    val gender: String?                = null,
    val country: String?               = null,
    val preferredGenres: List<String>? = null,
)
