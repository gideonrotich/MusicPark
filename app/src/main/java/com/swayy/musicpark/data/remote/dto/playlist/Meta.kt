package com.swayy.musicpark.data.remote.dto.playlist

data class Meta(
    val query: Query,
    val returnedCount: Int,
    val totalCount: Int
)