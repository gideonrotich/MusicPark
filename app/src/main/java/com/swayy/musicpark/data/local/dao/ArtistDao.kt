package com.swayy.musicpark.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.musicpark.data.local.entity.AlbumEntity
import com.swayy.musicpark.data.local.entity.ArtistEntity


@Dao
interface ArtistDao : BaseDao<ArtistEntity>{
    @Query("SELECT * FROM artist_table")
    suspend fun getArtist():List<ArtistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(list: List<ArtistEntity>)

    @Query("DELETE FROM artist_table")
    suspend fun deleteArtists()
}