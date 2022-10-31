package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface TopRepository {
    fun getTop(id:String):Flow<Resource<List<Track>>>
}