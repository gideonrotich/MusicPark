package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Song
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow


interface SongRepository {
    fun getSong(song:String):Flow<Resource<List<Song>>>
}