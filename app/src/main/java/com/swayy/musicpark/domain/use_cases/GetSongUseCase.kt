package com.swayy.musicpark.domain.use_cases

import com.swayy.musicpark.domain.models.Song
import com.swayy.musicpark.domain.repository.SongRepository
import com.swayy.musicpark.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    operator fun invoke(song:String):Flow<Resource<List<Song>>>{
        return repository.getSong(song)
    }
}