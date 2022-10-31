package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.TopRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopUseCase @Inject constructor(
    private val repository: TopRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Track>>>{
        return repository.getTop(id)
    }
}