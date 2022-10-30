package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Playlist
import com.swayy.musicpark.domain.repository.PlaylistRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PlaylistRepositoryImpl(
    private val napsterApi: NapsterApi
) : PlaylistRepository {
    override fun getPlaylists(id: String): Flow<Resource<List<Playlist>>> = flow{
        emit(Resource.Loading())

        try {
            val playlist = napsterApi.getPlaylists(id = id).tracks.map { it.toDomain() }
            emit(Resource.Success(playlist))
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

        val playlist = napsterApi.getPlaylists(id = id).tracks.map { it.toDomain() }
        emit(Resource.Success(playlist))
    }

}