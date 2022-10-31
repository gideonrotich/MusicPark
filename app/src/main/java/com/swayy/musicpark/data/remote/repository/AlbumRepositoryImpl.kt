package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.repository.AlbumRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AlbumRepositoryImpl (
    private val napsterApi: NapsterApi
        ) :AlbumRepository{
    override fun getAlbums(id: String): Flow<Resource<List<Album>>> = flow {
        emit(Resource.Loading())

        try {
            val album = napsterApi.getAlbums(id = id).albums.map { it.toDomain() }
            emit(Resource.Success(album))
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

        val album = napsterApi.getAlbums(id = id).albums.map { it.toDomain() }
        emit(Resource.Success(album))
    }
}