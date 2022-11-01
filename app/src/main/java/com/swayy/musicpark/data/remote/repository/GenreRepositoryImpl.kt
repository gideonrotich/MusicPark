package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.domain.repository.GenreRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GenreRepositoryImpl (
    private val napsterApi: NapsterApi
        ) : GenreRepository{
    override fun getGenre(): Flow<Resource<List<Genre>>> = flow{
        emit(Resource.Loading())

        try {
            val genre = napsterApi.getGenres().genres.map { it.toDomain() }
            emit(Resource.Success(genre))
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

        val genre = napsterApi.getGenres().genres.map { it.toDomain() }
        emit(Resource.Success(genre))
    }
}