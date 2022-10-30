package com.swayy.musicpark.presentation.screens.postdetails

import com.swayy.musicpark.domain.models.Playlist
import com.swayy.musicpark.domain.models.Post

data class PlaylistLIstState(
    val isLoading: Boolean = false,
    val error: String = "",
    val playlist: List<Playlist> = emptyList()
)
