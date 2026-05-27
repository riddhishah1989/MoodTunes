package com.moodtunes.app.data.remote.models

// DataResult.kt — only data layer concerns
sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
}

// Extension to convert ApiResponse → DataResult
fun <T> ApiResponse<T>.toResult(): DataResult<T> =
    if (success && data != null) DataResult.Success(data)
    else DataResult.Error(error ?: "Something went wrong.")

// Safe call wrapper — used in Repository
suspend fun <T> safeApiCall(
    call: suspend () -> ApiResponse<T>
): DataResult<T> =
    try { call().toResult() }
    catch (e: Exception) {
        DataResult.Error(e.message ?: "Network error.")
    }
