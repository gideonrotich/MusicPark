package com.swayy.musicpark.presentation.screens.artist.components

import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Post

data class ArtistDetailsListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val artistDetails: List<Artist> = emptyList()
)
