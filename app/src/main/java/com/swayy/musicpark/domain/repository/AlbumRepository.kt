package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbums(id:String):Flow<Resource<List<Album>>>
}