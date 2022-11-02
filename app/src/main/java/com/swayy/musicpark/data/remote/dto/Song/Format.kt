package com.swayy.musicpark.data.remote.dto.Song

data class Format(
    val bitrate: Int,
    val name: String,
    val sampleBits: Int,
    val sampleRate: Int,
    val type: String
)