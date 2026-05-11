package com.moodtunes.app.di

import android.content.Context
import androidx.room.Room
import com.moodtunes.app.data.local.FavouriteDao
import com.moodtunes.app.data.local.MoodSessionDao
import com.moodtunes.app.data.local.MoodTunesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MoodTunesDatabase =
        Room.databaseBuilder(
            context,
            MoodTunesDatabase::class.java,
            "moodtunes.db"
        ).build()

    @Provides
    fun provideMoodSessionDao(db: MoodTunesDatabase): MoodSessionDao = db.moodSessionDao()

    @Provides
    fun provideFavouriteDao(db: MoodTunesDatabase): FavouriteDao = db.favouriteDao()
}
