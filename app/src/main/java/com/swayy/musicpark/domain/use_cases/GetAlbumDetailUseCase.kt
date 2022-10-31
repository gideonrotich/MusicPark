package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.repository.AlbumDetailRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumDetailUseCase @Inject constructor(
    private val repository: AlbumDetailRepository
){
    operator fun invoke(id:String):Flow<Resource<List<Album>>>{
        return repository.getAlbumDetail(id)
    }
}