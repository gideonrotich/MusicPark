package com.swayy.musicpark.data.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swayy.musicpark.data.local.converters.Converters
import com.swayy.musicpark.data.local.dao.TrackDao
import com.swayy.musicpark.data.local.entity.TrackEntity

@TypeConverters(Converters::class)
@Database(
    entities = [TrackEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NapsterDatabase : RoomDatabase(){
    abstract fun TrackDao():TrackDao
}