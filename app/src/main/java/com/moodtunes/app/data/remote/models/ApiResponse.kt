package com.moodtunes.app.data.remote.models

import com.moodtunes.app.domain.result.DataResult

// ── Raw HTTP response wrapper ─────────────────────────────
// Every API call returns this from Retrofit
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

// ── Converts ApiResponse → DataResult ────────────────────
fun <T> ApiResponse<T>.toResult(): DataResult<T> =
    if (success && data != null) DataResult.Success(data)
    else DataResult.Error(error ?: "Something went wrong.")

// ── Safe API call wrapper — used in Repository ────────────
suspend fun <T> safeApiCall(
    call: suspend () -> ApiResponse<T>,
): DataResult<T> =
    try {
        call().toResult()
    } catch (e: Exception) {
        DataResult.Error(e.message ?: "Network error. Please check your connection.")
    }