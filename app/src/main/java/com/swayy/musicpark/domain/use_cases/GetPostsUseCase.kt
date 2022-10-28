package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.repository.getPosts
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository:getPosts
) {
    operator fun invoke(): Flow<Resource<List<Post>>>{
        return repository.getPosts()
    }
}