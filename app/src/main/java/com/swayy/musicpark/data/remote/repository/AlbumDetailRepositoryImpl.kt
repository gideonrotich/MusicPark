package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.repository.AlbumDetailRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AlbumDetailRepositoryImpl (
    private val napsterApi: NapsterApi
        ) :AlbumDetailRepository{
    override fun getAlbumDetail(id: String): Flow<Resource<List<Album>>> = flow{
        emit(Resource.Loading())

        try {
            val albumDetail = napsterApi.getAlbumDetails(id = id).albums.map { it.toDomain() }
            emit(Resource.Success(albumDetail))
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = "Connection Lost",
                )
            )
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                )
            )
        }

        val album = napsterApi.getAlbumDetails(id = id).albums.map { it.toDomain() }
        emit(Resource.Success(album))
    }
}