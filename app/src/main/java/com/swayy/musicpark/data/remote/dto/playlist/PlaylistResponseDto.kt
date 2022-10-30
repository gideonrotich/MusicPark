package com.swayy.musicpark.data.remote.dto.playlist

data class PlaylistResponseDto(
    val meta: Meta,
    val tracks: List<Track>
)