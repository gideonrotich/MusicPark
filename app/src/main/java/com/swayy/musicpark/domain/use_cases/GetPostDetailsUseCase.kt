package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.repository.PostDetailsRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val repository:PostDetailsRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Post>>>{
        return repository.getPostDetails(id)
    }
}