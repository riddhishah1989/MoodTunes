package com.moodtunes.app.data.remote.response

// POST /api/v1/auth/signin → ApiResponse<SignInResponse>
data class SignInResponse(
    val token: String,
    val user: UserResponse,
    val message: String?,
)
