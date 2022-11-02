package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Test
import com.swayy.musicpark.domain.repository.ArtistSearchRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistSearchUseCase @Inject constructor(
    private val repository: ArtistSearchRepository
) {
    operator fun invoke(artist:String):Flow<Resource<List<Test>>>{
        return repository.ArtistSearch(artist)
    }
}