package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.repository.ArtistRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(
    private val repository: ArtistRepository
) {
    operator fun invoke():Flow<Resource<List<Artist>>>{
        return repository.getArtists()
    }
}