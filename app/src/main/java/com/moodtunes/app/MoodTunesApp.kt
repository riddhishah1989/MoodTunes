package com.moodtunes.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoodTunesApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoodTunesApp
            private set  // ← only this class can set it

        // Access context anywhere
        val context: Context
            get() = instance.applicationContext
    }
}
