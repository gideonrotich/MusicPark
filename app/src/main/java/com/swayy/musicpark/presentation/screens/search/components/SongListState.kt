package com.swayy.musicpark.presentation.screens.search.components

import com.swayy.musicpark.domain.models.Song
import com.swayy.musicpark.domain.models.Test

data class SongListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val song: List<Song> = emptyList()
)
