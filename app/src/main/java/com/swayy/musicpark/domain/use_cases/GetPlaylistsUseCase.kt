package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Playlist
import com.swayy.musicpark.domain.repository.PlaylistRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(id:String):Flow<Resource<List<Playlist>>>{
        return repository.getPlaylists(id)
    }
}