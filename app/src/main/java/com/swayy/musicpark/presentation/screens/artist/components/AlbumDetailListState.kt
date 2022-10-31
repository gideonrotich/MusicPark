package com.swayy.musicpark.presentation.screens.artist.components

import com.swayy.musicpark.domain.models.Album

data class AlbumDetailListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val album: List<Album> = emptyList()
)
