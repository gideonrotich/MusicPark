package com.swayy.musicpark.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swayy.musicpark.data.local.entity.GenreEntity


@Dao
interface GenreDao: BaseDao<GenreEntity>{
    @Query("SELECT * FROM genre_table")
    suspend fun getGenre():List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(list: List<GenreEntity>)

    @Query("DELETE FROM genre_table")
    suspend fun deleteGenres()
}