package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumDetailRepository {
    fun getAlbumDetail(id:String):Flow<Resource<List<Album>>>
}