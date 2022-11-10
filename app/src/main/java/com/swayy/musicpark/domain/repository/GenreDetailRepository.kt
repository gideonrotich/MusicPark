package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface GenreDetailRepository {
    fun getGenreDetail(id:String):Flow<Resource<List<Genre>>>
}