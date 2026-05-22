package com.moodtunes.app.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit { putBoolean(KEY_IS_LOGGED_IN, value) }

    var hasSeenOnboarding: Boolean
        get() = prefs.getBoolean(KEY_HAS_SEEN_ONBOARDING, false)
        set(value) = prefs.edit { putBoolean(KEY_HAS_SEEN_ONBOARDING, value) }

    var jwtToken: String?
        get() = prefs.getString(KEY_JWT_TOKEN, null)
        set(value) = prefs.edit {
            if (value != null) putString(KEY_JWT_TOKEN, value) else remove(KEY_JWT_TOKEN)
        }

    fun clearAll() = prefs.edit { clear() }

    companion object {
        private const val PREFS_NAME = "moodtunes_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_HAS_SEEN_ONBOARDING = "has_seen_onboarding"
        private const val KEY_JWT_TOKEN = "jwt_token"
    }
}
