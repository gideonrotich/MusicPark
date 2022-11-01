package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Test
import com.swayy.musicpark.domain.repository.ArtistSearchRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ArtistSearchRepositoryImpl(
    private val napsterApi: NapsterApi
) :ArtistSearchRepository{
    override fun ArtistSearch(artist: String): Flow<Resource<List<Test>>> = flow{
        emit(Resource.Loading())

        try {
            val search = napsterApi.getArtistSearch(artist = artist).search.data.artists.map { it.toDomain() }
            emit(Resource.Success(search))
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

        val search = napsterApi.getArtistSearch(artist = artist).search.data.artists.map { it.toDomain() }
        emit(Resource.Success(search))
    }

}