package com.swayy.musicpark.data.remote.dto.tracks

data class Format(
    val bitrate: Int,
    val name: String,
    val sampleBits: Int,
    val sampleRate: Int,
    val type: String
)