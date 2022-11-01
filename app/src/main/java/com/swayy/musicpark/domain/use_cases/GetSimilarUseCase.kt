package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.repository.SimilarRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val repository: SimilarRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Artist>>>{
        return repository.getSimilar(id)
    }
}