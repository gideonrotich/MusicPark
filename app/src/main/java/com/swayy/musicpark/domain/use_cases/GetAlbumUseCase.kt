package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.repository.AlbumRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Album>>>{
        return repository.getAlbums(id)
    }
}