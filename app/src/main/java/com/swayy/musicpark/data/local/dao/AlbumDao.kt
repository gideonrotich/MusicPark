package com.swayy.musicpark.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.musicpark.data.local.entity.AlbumEntity

@Dao
interface AlbumDao : BaseDao<AlbumEntity> {
    @Query("SELECT * FROM album_table")
    suspend fun getAlbums():List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(list: List<AlbumEntity>)

    @Query("DELETE FROM album_table")
    suspend fun deleteAlbums()
}