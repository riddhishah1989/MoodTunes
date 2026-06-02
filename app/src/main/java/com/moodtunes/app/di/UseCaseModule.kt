package com.moodtunes.app.di

import com.moodtunes.app.data.repository.MoodTunesRepositoryImpl
import com.moodtunes.app.domain.repository.IMoodTunesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindMoodTunesRepository(impl: MoodTunesRepositoryImpl): IMoodTunesRepository
}
