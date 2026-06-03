package com.moodtunes.app.data.remote.response

import com.moodtunes.app.data.remote.models.ApiResponse
import com.moodtunes.app.domain.result.DataResult

suspend fun <T> safeApiCall(
    call: suspend () -> retrofit2.Response<ApiResponse<T>>,
): DataResult<T> {
    return try {
        val response = call()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.success && body.data != null) {
                DataResult.Success(body.data)
            } else {
                DataResult.Error(body?.error ?: "Something went wrong.")
            }
        } else {
            // ← handles 400, 401, 404, 500 etc
            val errorBody = response.errorBody()?.string()
            val error = try {
                // Try to parse error message from JSON
                org.json.JSONObject(errorBody ?: "").getString("error")
            } catch (e: Exception) {
                "Something went wrong."
            }
            DataResult.Error(error)
        }

    } catch (e: java.io.IOException) {
        // No internet
        DataResult.Error("Network error. Please check your connection.")
    } catch (e: Exception) {
        // Unexpected error
        DataResult.Error(e.message ?: "Something went wrong.")
    }
}