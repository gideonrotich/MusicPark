package com.swayy.musicpark.presentation.screens.artist.components

import com.swayy.musicpark.domain.models.Artist

data class SImilarListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val artists: List<Artist> = emptyList()
)
