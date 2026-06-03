package com.moodtunes.app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.moodtunes.app.domain.model.User
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "moodtunes_user_prefs")

@Singleton
class UserPreferences @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val gson: Gson,
) {

    private companion object {
        // Auth
        val KEY_API_TOKEN = stringPreferencesKey("jwt_token")
        val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val KEY_TOKEN_EXPIRY = stringPreferencesKey("token_expiry")

        // User
        val KEY_USER = stringPreferencesKey("user")

        // App settings
        val KEY_SONG_COUNT = intPreferencesKey("default_song_count")
        val KEY_INCLUDE_SPOTIFY = booleanPreferencesKey("include_spotify")
        val KEY_INCLUDE_YOUTUBE = booleanPreferencesKey("include_youtube")
        val KEY_IS_ONBOARDED = booleanPreferencesKey("is_onboarded")
    }

    suspend fun saveToken(token: String) {
        // Save token + expiry time (current time + 24 hours)
        val expiryTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000)
        context.dataStore.edit { prefs ->
            prefs[KEY_API_TOKEN] = token
            prefs[KEY_IS_LOGGED_IN] = true
            prefs[KEY_TOKEN_EXPIRY] = expiryTime.toString()
        }
    }

    suspend fun getToken(): String? =
        context.dataStore.data.first()[KEY_API_TOKEN]

    suspend fun isLoggedIn(): Boolean =
        context.dataStore.data.first()[KEY_IS_LOGGED_IN] ?: false

    // Check if token is expired
    suspend fun isTokenExpired(): Boolean {
        val expiry = context.dataStore.data.first()[KEY_TOKEN_EXPIRY]
            ?.toLongOrNull() ?: return true
        return System.currentTimeMillis() > expiry
    }

    suspend fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        context.dataStore.edit { prefs ->
            prefs[KEY_USER] = userJson
        }
    }

    suspend fun getUser(): User? {
        val userJson = context.dataStore.data.first()[KEY_USER]
        return userJson?.let { gson.fromJson(it, User::class.java) }
    }

    suspend fun updateUser(user: User) = saveUser(user)

    suspend fun getSongCount(): Int =
        context.dataStore.data.first()[KEY_SONG_COUNT] ?: 8

    suspend fun saveSongCount(count: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_SONG_COUNT] = count.coerceIn(1, 15)
        }
    }

    suspend fun getIncludeSpotify(): Boolean =
        context.dataStore.data.first()[KEY_INCLUDE_SPOTIFY] ?: true

    suspend fun saveIncludeSpotify(include: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_INCLUDE_SPOTIFY] = include
        }
    }

    suspend fun getIncludeYoutube(): Boolean =
        context.dataStore.data.first()[KEY_INCLUDE_YOUTUBE] ?: true

    suspend fun saveIncludeYoutube(include: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_INCLUDE_YOUTUBE] = include
        }
    }

    suspend fun isOnboarded(): Boolean =
        context.dataStore.data.first()[KEY_IS_ONBOARDED] ?: false

    suspend fun setOnboarded() {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_ONBOARDED] = true
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}