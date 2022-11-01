package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Song
import com.swayy.musicpark.domain.repository.SongRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SongRepositoryImpl(
    private val napsterApi: NapsterApi
) : SongRepository {
    override fun getSong(song: String): Flow<Resource<List<Song>>> = flow {
        emit(Resource.Loading())

        try {
            val songs = napsterApi.getSong(song = song).search.data.tracks.map { it.toDomain() }
            emit(Resource.Success(songs))
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

        val songs = napsterApi.getSong(song = song).search.data.tracks.map { it.toDomain() }
        emit(Resource.Success(songs))
    }

}