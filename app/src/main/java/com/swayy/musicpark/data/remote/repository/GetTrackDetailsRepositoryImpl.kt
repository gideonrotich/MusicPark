package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.GetTrackDetailsRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTrackDetailsRepositoryImpl(
    private val napsterApi: NapsterApi,
) :GetTrackDetailsRepository {

    override suspend fun getTrackDetails(trackId: String): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading())

        try {
            val trackDetails = napsterApi.getTrackDetails(trackId = trackId).tracks.map { it.toDomain() }
            emit(Resource.Success(trackDetails))
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

        val trackDetails = napsterApi.getTrackDetails(trackId = trackId).tracks.map { it.toDomain() }
        emit(Resource.Success(trackDetails))
    }
}