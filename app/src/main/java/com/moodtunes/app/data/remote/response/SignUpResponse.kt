package com.moodtunes.app.data.remote.response

// POST /api/v1/auth/signup → ApiResponse<SignUpResponse>
data class SignUpResponse(
    val token: String,
    val user: UserResponse,
    val message: String?,
)

data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val preferredGenres: List<String>?,
    val isVerified: Boolean,
    val lastLogin: String?,
    val createdAt: String,
)
