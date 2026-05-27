package com.moodtunes.app.domain.model

data class Auth(
    val token: String,
    val user: User,
    val message: String?,
)
