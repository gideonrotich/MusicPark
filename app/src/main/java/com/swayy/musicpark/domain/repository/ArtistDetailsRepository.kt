package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArtistDetailsRepository {
    fun getArtistDetails(id:String):Flow<Resource<List<Artist>>>
}