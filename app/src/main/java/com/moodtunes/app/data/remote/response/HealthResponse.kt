package com.moodtunes.app.data.remote.response

// GET /api/v1/health → ApiResponse<HealthResponse>
data class HealthResponse(
    val status: String,
    val service: String,
    val version: String,
    val timestamp: String,
    val uptimeSeconds: Int?,
    val database: DatabaseStatusResponse?,
    val environment: String?,
)

data class DatabaseStatusResponse(
    val status: String,
    val connected: Boolean,
)
