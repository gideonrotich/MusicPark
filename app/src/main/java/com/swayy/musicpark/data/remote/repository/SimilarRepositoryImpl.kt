package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.repository.SimilarRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SimilarRepositoryImpl(
    private val napsterApi: NapsterApi
) :SimilarRepository{
    override fun getSimilar(id: String): Flow<Resource<List<Artist>>> = flow{
        emit(Resource.Loading())

        try {
            val similar = napsterApi.getSimilar(id = id).artists.map { it.toDomain() }
            emit(Resource.Success(similar))
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

        val similar = napsterApi.getSimilar(id = id).artists.map { it.toDomain() }
        emit(Resource.Success(similar))
    }

}