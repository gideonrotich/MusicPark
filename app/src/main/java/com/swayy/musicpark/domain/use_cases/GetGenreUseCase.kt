package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.domain.repository.GenreRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    operator fun invoke():Flow<Resource<List<Genre>>>{
        return repository.getGenre()
    }
}