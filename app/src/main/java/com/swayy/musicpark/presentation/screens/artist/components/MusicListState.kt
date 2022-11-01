package com.swayy.musicpark.presentation.screens.artist.components

import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.models.Track

data class MusicListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val music: List<Track> = emptyList()
)
