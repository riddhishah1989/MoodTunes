package com.moodtunes.app.data.remote.response

// ── POST /api/v1/auth/forgot-password ─────────────────────────
// ── POST /api/v1/auth/resend-otp      ─────────────────────────
data class ForgotPasswordResponse(
    val message: String,
)

