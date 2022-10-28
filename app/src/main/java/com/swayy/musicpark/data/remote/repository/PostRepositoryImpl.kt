package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.repository.getPosts
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val napsterApi: NapsterApi
) : getPosts {
    override fun getPosts(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())

        try {
            val posts = napsterApi.getPosts().posts.map { it.toDomain() }
            emit(Resource.Success(posts))
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

        val posts = napsterApi.getPosts().posts.map { it.toDomain() }
        emit(Resource.Success(posts))
    }
}