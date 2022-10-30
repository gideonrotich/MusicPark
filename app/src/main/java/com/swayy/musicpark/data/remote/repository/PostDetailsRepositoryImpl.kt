package com.swayy.musicpark.data.remote.repository

import com.swayy.musicpark.data.remote.api.NapsterApi
import com.swayy.musicpark.data.remote.mapper.toDomain
import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.repository.PostDetailsRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PostDetailsRepositoryImpl(
    private val napsterApi: NapsterApi
): PostDetailsRepository {
    override fun getPostDetails(id: String): Flow<Resource<List<Post>>> = flow{
        emit(Resource.Loading())

        try {
            val postDetails = napsterApi.getPostDetails(id = id).posts.map { it.toDomain() }
            emit(Resource.Success(postDetails))
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

        val postDetails = napsterApi.getPostDetails(id = id).posts.map { it.toDomain() }
        emit(Resource.Success(postDetails))
    }

}