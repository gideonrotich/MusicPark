package com.swayy.musicpark.presentation.screens.tracksDetails

import com.swayy.musicpark.domain.models.Track

data class TrackDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val trackDetails: List<Track> = emptyList()
)
