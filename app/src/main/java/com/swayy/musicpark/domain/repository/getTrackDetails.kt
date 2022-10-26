package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetTrackDetailsRepository {
   suspend fun getTrackDetails(trackId: String) : Flow<Resource<List<Track>>>
}