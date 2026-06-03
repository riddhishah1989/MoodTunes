package com.moodtunes.app.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val preferredGenres: List<String>,
    val isVerified: Boolean,
    val lastLogin: String?,
    val createdAt: String,
)
