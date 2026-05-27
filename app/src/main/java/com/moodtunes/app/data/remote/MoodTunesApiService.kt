package com.moodtunes.app.data.remote

import com.moodtunes.app.data.remote.models.ApiResponse
import com.moodtunes.app.data.remote.request.*
import com.moodtunes.app.data.remote.response.*
import retrofit2.http.*

interface MoodTunesApiService {

    // ── Health ────────────────────────────────────────────────
    @GET("api/v1/health")
    suspend fun healthCheck(): ApiResponse<HealthResponse>

    // ── Auth ──────────────────────────────────────────────────
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): ApiResponse<SignUpResponse>

    @POST("api/v1/auth/signin")
    suspend fun signIn(@Body request: SignInRequest): ApiResponse<SignInResponse>

    @GET("api/v1/auth/me")
    suspend fun getMe(): ApiResponse<UserResponse>

    @PUT("api/v1/auth/profile")
    suspend fun updateProfile(@Body request: UpdateProfileRequest): ApiResponse<UpdateProfileResponse>

    @PUT("api/v1/auth/password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): ApiResponse<Unit>

    @DELETE("api/v1/auth/account")
    suspend fun deleteAccount(@Body request: DeleteAccountRequest): ApiResponse<Unit>

    // ── Moods ─────────────────────────────────────────────────
    @GET("api/v1/moods")
    suspend fun getMoods(): ApiResponse<MoodsResponse>

    // ── Genres ────────────────────────────────────────────────
    @GET("api/v1/genres")
    suspend fun getGenres(): ApiResponse<GenresResponse>

    // ── Recommendations ───────────────────────────────────────
    @POST("api/v1/recommendations")
    suspend fun getRecommendations(
        @Body request: RecommendationRequest
    ): ApiResponse<RecommendationResponse>

    // ── History ───────────────────────────────────────────────
    @GET("api/v1/history")
    suspend fun getHistory(
        @Query("limit")  limit: Int  = 20,
        @Query("offset") offset: Int = 0,
    ): ApiResponse<HistoryResponse>

    @GET("api/v1/history/{id}")
    suspend fun getSession(@Path("id") id: String): ApiResponse<SessionResponse>

    @DELETE("api/v1/history/{id}")
    suspend fun deleteSession(@Path("id") id: String): ApiResponse<Unit>

    @DELETE("api/v1/history")
    suspend fun clearHistory(): ApiResponse<Unit>

    // ── Favourites ────────────────────────────────────────────
    @GET("api/v1/favourites")
    suspend fun getFavourites(
        @Query("limit")  limit: Int  = 50,
        @Query("offset") offset: Int = 0,
    ): ApiResponse<FavouritesResponse>

    @POST("api/v1/favourites")
    suspend fun addFavourite(@Body request: AddFavouriteRequest): ApiResponse<AddFavouriteResponse>

    @GET("api/v1/favourites/{songId}/check")
    suspend fun checkFavourite(@Path("songId") songId: String): ApiResponse<CheckFavouriteResponse>

    @DELETE("api/v1/favourites/{songId}")
    suspend fun removeFavourite(@Path("songId") songId: String): ApiResponse<Unit>

    @DELETE("api/v1/favourites")
    suspend fun clearFavourites(): ApiResponse<Unit>

    // ── Journal ───────────────────────────────────────────────
    @GET("api/v1/journal")
    suspend fun getJournal(
        @Query("limit")  limit: Int  = 20,
        @Query("offset") offset: Int = 0,
    ): ApiResponse<JournalResponse>

    @POST("api/v1/journal")
    suspend fun addJournalEntry(@Body request: AddJournalRequest): ApiResponse<JournalEntryResponse>

    @GET("api/v1/journal/{id}")
    suspend fun getJournalEntry(@Path("id") id: String): ApiResponse<JournalEntryResponse>

    @DELETE("api/v1/journal/{id}")
    suspend fun deleteJournalEntry(@Path("id") id: String): ApiResponse<Unit>

    @DELETE("api/v1/journal")
    suspend fun clearJournal(): ApiResponse<Unit>

    // ── Insights ──────────────────────────────────────────────
    @GET("api/v1/insights")
    suspend fun getInsights(@Query("days") days: Int = 30): ApiResponse<InsightsResponse>

    // ── Share ─────────────────────────────────────────────────
    @POST("api/v1/share")
    suspend fun generateShareLink(@Body request: ShareRequest): ApiResponse<ShareResponse>

    @GET("api/v1/share/{sessionId}")
    suspend fun getSharedPlaylist(@Path("sessionId") sessionId: String): ApiResponse<SharedPlaylistResponse>

    // ── Spotify ───────────────────────────────────────────────
    @GET("api/v1/spotify/search")
    suspend fun searchSpotify(
        @Query("q")     query: String,
        @Query("limit") limit: Int = 1,
    ): ApiResponse<SpotifySearchResponse>

    // ── YouTube ───────────────────────────────────────────────
    @GET("api/v1/youtube/search")
    suspend fun searchYouTube(
        @Query("q")     query: String,
        @Query("limit") limit: Int = 1,
    ): ApiResponse<YouTubeSearchResponse>
}
