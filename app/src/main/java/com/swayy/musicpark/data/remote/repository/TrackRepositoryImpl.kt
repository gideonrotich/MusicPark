package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.local.dao.TrackDao
import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.data.remote.mapper.toEntity
import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.domain.repository.TrackRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TrackRepositoryImpl(
    private val napsterApi: NapsterApi,
    private val trackDao: TrackDao
) : TrackRepository {
    override fun getTracks(): Flow<Resource<List<Track>>> = flow {
        emit(Resource.Loading())

        val tracksFromDb =  trackDao.getTracks()
        emit(Resource.Loading(data = tracksFromDb.map { it.toDomain() }))


        try {
           val apiResponse = napsterApi.getTracks()
            trackDao.deleteTracks()
            trackDao.insertTracks(apiResponse.tracks.map { it.toEntity() })
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = "Connection Lost",
                    data = null
                )
            )
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = null
                )
            )
        }

        val allTracks = trackDao.getTracks().map { it.toDomain() }
//        val allTracks = napsterApi.getTracks().tracks.map { it.toDomain() }
        emit(Resource.Success(allTracks))
    }

}