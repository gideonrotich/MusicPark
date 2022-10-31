package com.swayy.musicpark.presentation.screens.artist

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Track

data class TopListState (
    val isLoading: Boolean = false,
    val error: String = "",
    val top: List<Track> = emptyList()
)