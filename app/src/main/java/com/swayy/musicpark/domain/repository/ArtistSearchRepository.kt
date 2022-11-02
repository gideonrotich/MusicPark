package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Test
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArtistSearchRepository {
    fun ArtistSearch(artist:String):Flow<Resource<List<Test>>>
}