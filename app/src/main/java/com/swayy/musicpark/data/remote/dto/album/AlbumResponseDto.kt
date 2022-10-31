package com.swayy.musicpark.data.remote.dto.album

data class AlbumResponseDto(
    val albums: List<Album>,
    val meta: Meta
)