package com.swayy.musicpark.presentation.screens.tracks

import com.swayy.musicpark.domain.models.Track

data class TrackListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val tracks: List<Track> = emptyList()
)
