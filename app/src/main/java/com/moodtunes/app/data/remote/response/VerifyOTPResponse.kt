package com.moodtunes.app.data.remote.response

data class VerifyOTPResponse(
    val email: String,   // passed to ResetPassword screen
    val message: String,
)