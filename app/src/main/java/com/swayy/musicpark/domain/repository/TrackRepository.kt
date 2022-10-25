package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getTracks():Flow<Resource<List<Track>>>
}