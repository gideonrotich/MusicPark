package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.TrackRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTracksUseCase @Inject constructor(
    private val repository: TrackRepository
) {
    operator fun invoke(): Flow<Resource<List<Track>>> {
        return repository.getTracks()
    }
}