package com.moodtunes.app.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodSessionDao {
    @Query("SELECT * FROM mood_sessions ORDER BY timestamp DESC")
    fun getAllSessions(): Flow<List<MoodSessionEntity>>

    @Query("SELECT * FROM mood_sessions WHERE sessionId = :id")
    suspend fun getSession(id: String): MoodSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: MoodSessionEntity)

    @Query("DELETE FROM mood_sessions")
    suspend fun clearAll()
}

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourites ORDER BY savedAt DESC")
    fun getAllFavourites(): Flow<List<FavouriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE songId = :id)")
    fun isFavourite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(fav: FavouriteEntity)

    @Query("DELETE FROM favourites WHERE songId = :id")
    suspend fun removeFavourite(id: String)

    @Query("DELETE FROM favourites")
    suspend fun clearAll()
}

@Database(
    entities = [MoodSessionEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SongListConverter::class)
abstract class MoodTunesDatabase : RoomDatabase() {
    abstract fun moodSessionDao(): MoodSessionDao
    abstract fun favouriteDao(): FavouriteDao
}
