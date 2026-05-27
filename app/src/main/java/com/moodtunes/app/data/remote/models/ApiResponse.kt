package com.moodtunes.app.data.remote.models

data class ApiResponse<T>(
    val success: Boolean,
    val data: T?,
    val error: String?,
    val message: String?,
    val meta: MetaData?,
)

data class MetaData(
    val durationMs: Int?,
    val model: String?,
)
