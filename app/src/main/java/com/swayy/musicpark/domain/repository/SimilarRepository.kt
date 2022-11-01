package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface SimilarRepository {
    fun getSimilar(id:String):Flow<Resource<List<Artist>>>
}