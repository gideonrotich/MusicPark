package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.repository.ArtistDetailsRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistDetailsUseCase @Inject constructor(
    private val repository: ArtistDetailsRepository
) {
    operator fun invoke(id:String): Flow<Resource<List<Artist>>> {
        return repository.getArtistDetails(id)
    }
}