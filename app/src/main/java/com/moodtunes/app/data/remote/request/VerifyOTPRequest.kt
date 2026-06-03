package com.moodtunes.app.data.remote.request

data class VerifyOTPRequest(
    val email: String,
    val otp: String,
)