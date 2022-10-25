package com.swayy.musicpark.data.remote.dto.tracks

data class LosslessFormat(
    val bitrate: Int,
    val name: String,
    val sampleBits: Int,
    val sampleRate: Int,
    val type: String
)