package com.swayy.musicpark.domain.repository

import com.swayy.musicpark.domain.models.Playlist
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getPlaylists(id:String):Flow<Resource<List<Playlist>>>
}