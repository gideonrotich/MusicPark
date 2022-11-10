package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.domain.repository.GenreDetailRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreDetailsUseCase @Inject constructor(
    private val repository: GenreDetailRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Genre>>>{
        return repository.getGenreDetail(id)
    }
}