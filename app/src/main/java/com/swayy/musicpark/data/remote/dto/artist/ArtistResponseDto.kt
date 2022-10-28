package com.swayy.musicpark.data.remote.dto.artist

data class ArtistResponseDto(
    val artists: List<Artist>,
    val meta: Meta
)