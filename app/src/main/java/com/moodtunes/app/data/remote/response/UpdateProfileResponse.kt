package com.moodtunes.app.data.remote.response

// PUT /api/v1/auth/profile → ApiResponse<UpdateProfileResponse>
data class UpdateProfileResponse(
    val user: UserResponse,
    val message: String?,
)
