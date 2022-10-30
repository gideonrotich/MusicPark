package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostDetailsRepository {
    fun getPostDetails(id:String): Flow<Resource<List<Post>>>
}