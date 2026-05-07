package com.moodtunes.app.data.remote

import com.moodtunes.app.data.remote.response.MoodsResponse
import com.moodtunes.app.data.remote.response.RecommendationRequest
import com.moodtunes.app.data.remote.response.RecommendationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MoodTunesApiService {

    @GET("api/v1/health")
    suspend fun healthCheck(): Response<Map<String, Any>>

    @GET("api/v1/moods")
    suspend fun getMoods(): Response<MoodsResponse>

    @POST("api/v1/recommendations")
    suspend fun getRecommendations(
        @Body request: RecommendationRequest
    ): Response<RecommendationResponse>
}
