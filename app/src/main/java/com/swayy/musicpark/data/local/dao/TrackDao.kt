package com.swayy.musicpark.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.musicpark.data.local.entity.TrackEntity


@Dao
interface TrackDao {
    @Query("SELECT * FROM tracks_table")
    suspend fun getTracks():List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(list: List<TrackEntity>)

    @Query("DELETE FROM tracks_table")
    suspend fun deleteTracks()
}