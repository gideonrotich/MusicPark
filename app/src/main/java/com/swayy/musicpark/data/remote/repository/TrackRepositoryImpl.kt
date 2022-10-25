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

//        val getTracksfromDb = trackDao.getTracks().map { it.toDomain() }
//        emit(Resource.Success(data = getTracksfromDb))

        try {
//            val apiResponse = napsterApi.getTracks()
//            trackDao.deleteTracks()
//            trackDao.insertTracks(apiResponse.tracks.map { it.toEntity() })
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
//        val trackData = trackDao.getTracks().map { it.toDomain() }
//        emit(Resource.Success(trackData))
        val allMatches = napsterApi.getTracks().tracks.map { it.toDomain() }
        emit(Resource.Success(allMatches))
    }

}