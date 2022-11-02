package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getGenre():Flow<Resource<List<Genre>>>
}