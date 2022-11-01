package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.MusicRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMusicUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    operator fun invoke(id:String): Flow<Resource<List<Track>>>{
        return repository.getMusic(id)
    }
}