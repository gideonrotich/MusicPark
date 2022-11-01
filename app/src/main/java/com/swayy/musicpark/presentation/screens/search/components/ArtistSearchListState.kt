package com.swayy.musicpark.presentation.screens.search.components

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Test
import com.swayy.musicpark.domain.models.Track

data class ArtistSearchListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val artist: List<Test> = emptyList()
)
