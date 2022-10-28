package com.swayy.musicpark.presentation.screens.artist

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Post

data class ArtistListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val artists: List<Artist> = emptyList()
)
