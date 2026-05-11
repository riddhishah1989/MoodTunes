package com.moodtunes.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.moodtunes.app.domain.model.Song

@Entity(tableName = "mood_sessions")
@TypeConverters(SongListConverter::class)
data class MoodSessionEntity(
    @PrimaryKey val sessionId: String,
    val moodId: String,
    val moodLabel: String,
    val moodEmoji: String,
    val customText: String?,
    val moodInterpretation: String,
    val songs: List<Song>,
    val timestamp: Long,
)
