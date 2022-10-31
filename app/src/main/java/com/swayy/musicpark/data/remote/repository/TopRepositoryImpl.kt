package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.TopRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TopRepositoryImpl(
    private val napsterApi: NapsterApi
) : TopRepository{
    override fun getTop(id: String): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading())

        try {
            val top = napsterApi.getTop(id = id).tracks.map { it.toDomain() }
            emit(Resource.Success(top))
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

        val top = napsterApi.getTop(id = id).tracks.map { it.toDomain() }
        emit(Resource.Success(top))
    }
}