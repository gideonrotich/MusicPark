package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.GetTrackDetailsRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

class GetTrackDetailsUseCase(
    private val repository: GetTrackDetailsRepository
) {

    suspend  operator fun invoke(trackId:String)  : Flow<Resource<List<Track>>> {
        return repository.getTrackDetails(trackId)
    }
}