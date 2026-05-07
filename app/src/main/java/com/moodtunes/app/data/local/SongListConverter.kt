package com.moodtunes.app.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moodtunes.app.domain.model.Song

class SongListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSongList(songs: List<Song>): String = gson.toJson(songs)

    @TypeConverter
    fun toSongList(json: String): List<Song> {
        val type = object : TypeToken<List<Song>>() {}.type
        return gson.fromJson(json, type)
    }
}
