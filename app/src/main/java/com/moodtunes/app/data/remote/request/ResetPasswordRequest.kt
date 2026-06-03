package com.moodtunes.app.data.remote.request

data class ResetPasswordRequest(
    val email: String,
    val newPassword: String,
    val confirmPassword: String,
)