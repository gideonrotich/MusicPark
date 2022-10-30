package com.swayy.musicpark.data.remote.dto.playlist

data class Query(
    val limit: Int,
    val next: String,
    val offset: Int
)