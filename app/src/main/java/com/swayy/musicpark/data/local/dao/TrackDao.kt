package com.swayy.musicpark.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.musicpark.data.local.entity.TrackEntity


@Dao
interface TrackDao : BaseDao<TrackEntity> {

    @Query("SELECT * FROM TrackEntity")
    suspend fun getTracks():List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(list: List<TrackEntity>)

    @Query("DELETE FROM TrackEntity")
    suspend fun deleteTracks()
}