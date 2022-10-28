package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.repository.ArtistRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ArtistRepositoryImpl(
    private val napsterApi: NapsterApi
): ArtistRepository {
    override fun getArtists(): Flow<Resource<List<Artist>>> = flow {
        emit(Resource.Loading())

        try {
            val artist = napsterApi.getArtists().artists.map { it.toDomain() }
            emit(Resource.Success(artist))
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

        val artist = napsterApi.getArtists().artists.map { it.toDomain() }
        emit(Resource.Success(artist))
    }
}