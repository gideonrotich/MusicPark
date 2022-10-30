package com.swayy.musicpark.data.remote.dto.playlist

data class Format(
    val bitrate: Int,
    val name: String,
    val sampleBits: Int,
    val sampleRate: Int,
    val type: String
)